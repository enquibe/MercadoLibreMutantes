package com.mercadolibre.mutants;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MutantController.class)
public class MutantControllerTests {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Disabled
    void returnStatus200_ifMutant() {

    }

    @Test
    @Disabled
    void returnStatus403_ifHuman() {

    }

    @Test
    void returnsStatus400_ifInvalidInput_Letters() throws Exception {
        String[] invalidLetterDna = { "ATGCGA", "CAGTGC", "TKTATT", "AGACGG", "GCGTCA", "TCACTG" };
        DetectMutantRequest request = new DetectMutantRequest(invalidLetterDna);
        String body = objectMapper.writeValueAsString(request);

        mockMvc
            .perform(post("/mutants")
                .contentType("application/json")
                .content(body))
            .andExpect(status().isBadRequest());
    }

    @Test
    @Disabled
    void returnsStatus400_ifInvalidInput_MatrixRows() {
        String[] missingRowDna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA" };
    }

    @Test
    @Disabled
    void returnsStatus400_ifInvalidInput_MatrixCols() {
        String[] missingColDna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
    }
}
