package com.mercadolibre.mutants;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.mercadolibre.mutants.repository.DnaRepository;
import com.mercadolibre.mutants.web.StatsController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = StatsController.class)
public class StatsControllerTests {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean 
    private DnaRepository dnaRepository;

    @Test
    void returnsStats() throws Exception {
        when(dnaRepository.countByIsMutant(true)).thenReturn(40L);
        when(dnaRepository.countByIsMutant(false)).thenReturn(100L);

        String expectedJson = "{\"count_mutant_dna\":40,\"count_human_dna\":100,\"ratio\":0.4}";

        mockMvc.perform(get("/stats")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json(expectedJson));
    }
}
