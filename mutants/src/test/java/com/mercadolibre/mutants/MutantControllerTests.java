package com.mercadolibre.mutants;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.mutants.detector.DetectMutantRequest;
import com.mercadolibre.mutants.detector.MutantController;
import com.mercadolibre.mutants.detector.MutantDetector;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(controllers = MutantController.class)
public class MutantControllerTests {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean 
    private MutantDetector mutantDetector;

    @Test
    void returnStatus200_ifMutant() throws Exception {
        String[] mutantDna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };

        when(mutantDetector.isMutant(eq(mutantDna))).thenReturn(true);

        performMutantsPost(mutantDna).andExpect(status().isOk());
    }

    @Test
    void returnStatus403_ifHuman() throws Exception {
        String[] humanDna = { "ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG" };

        when(mutantDetector.isMutant(eq(humanDna))).thenReturn(false);

        performMutantsPost(humanDna).andExpect(status().isForbidden());
    }

    @Test
    void returnStatus400_ifInvalidInput_Letters() throws Exception {
        String[] invalidLetterDna = { "ATGCGA", "CAGTGC", "TKTATT", "AGACGG", "GCGTCA", "TCACTG" };
        
        performMutantsPost(invalidLetterDna).andExpect(status().isBadRequest());
    }

    @Test
    void returnStatus400_ifInvalidInput_DnaRows() throws Exception {
        String[] missingRowDna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA" };

        performMutantsPost(missingRowDna).andExpect(status().isBadRequest());
    }

    @Test
    void returnStatus400_ifInvalidInput_DnaCols() throws Exception {
        String[] missingColDna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACT" };

        performMutantsPost(missingColDna).andExpect(status().isBadRequest());
    }

    @Test
    void returnStatus400_ifInvalidInput_DnaEmpty() throws Exception {
        String[] emptyDna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACT" };

        performMutantsPost(emptyDna).andExpect(status().isBadRequest());
    }

    @Test
    void returnStatus400_ifInvalidInput_DnaNull() throws Exception {
        performMutantsPost(null).andExpect(status().isBadRequest());
    }

    @Test
    void returnStatus400_ifInvalidInput_DnaRowsAndChars() throws Exception {
        String[] missingRowInvalidCharDna = { "ATGCGA", "CAGTGC", "TTAKGT", "AGAAGG", "CCCCTA" };

        performMutantsPost(missingRowInvalidCharDna).andExpect(status().isBadRequest());
    }

    ResultActions performMutantsPost(String[] dna) throws Exception {
        DetectMutantRequest request = new DetectMutantRequest(dna);
        String body = objectMapper.writeValueAsString(request);

        return mockMvc.perform(post("/mutants")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));
    }
}
