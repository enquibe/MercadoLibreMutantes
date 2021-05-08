package com.mercadolibre.mutants;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(controllers = MutantController.class)
public class MutantControllerTests {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Disabled
    void returnStatus200_ifMutant() throws Exception {
        String[] mutantDna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };

        performMutantsPost(mutantDna).andExpect(status().isOk());
    }

    @Test
    @Disabled
    void returnStatus403_ifHuman() throws Exception {
        String[] humanDna = { "ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG" };

        performMutantsPost(humanDna).andExpect(status().isForbidden());
    }

    @Test
    void returnStatus400_ifInvalidInput_Letters() throws Exception {
        String[] invalidLetterDna = { "ATGCGA", "CAGTGC", "TKTATT", "AGACGG", "GCGTCA", "TCACTG" };
        
        performMutantsPost(invalidLetterDna).andExpect(status().isBadRequest());
    }

    @Test
    @Disabled
    void returnStatus400_ifInvalidInput_MatrixRows() throws Exception {
        String[] missingRowDna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA" };

        performMutantsPost(missingRowDna)
            .andExpect(status().isBadRequest());
            // .andExpect(jsonPath("$.timestamp", is(notNullValue())))
            // .andExpect(jsonPath("$.status", is(400)))
            // .andExpect(jsonPath("$.errors").isArray())
            // .andExpect(jsonPath("$.errors", hasSize(3)))
            // .andExpect(jsonPath("$.errors", hasItem("Author is not allowed.")))
    }

    @Test
    @Disabled
    void returnStatus400_ifInvalidInput_MatrixCols() throws Exception {
        String[] missingColDna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACT" };

        performMutantsPost(missingColDna).andExpect(status().isBadRequest());
    }

    // TODO: Empty matrix. Null matrix

    ResultActions performMutantsPost(String[] dna) throws Exception {
        DetectMutantRequest request = new DetectMutantRequest(dna);
        String body = objectMapper.writeValueAsString(request);

        return mockMvc.perform(post("/mutants")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));
    }
}
