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
    void returnStatus400_ifInvalidInput_DnaRows() throws Exception {
        String[] missingRowDna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA" };

        performMutantsPost(missingRowDna).andExpect(status().isBadRequest());
    }

    // TODO: Otro unit test inyectando Validator? 
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

    // TODO: Todos los mensajes de error juntos (ej: caracter y columna)

    ResultActions performMutantsPost(String[] dna) throws Exception {
        DetectMutantRequest request = new DetectMutantRequest(dna);
        String body = objectMapper.writeValueAsString(request);

        return mockMvc.perform(post("/mutants")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));
    }
}
