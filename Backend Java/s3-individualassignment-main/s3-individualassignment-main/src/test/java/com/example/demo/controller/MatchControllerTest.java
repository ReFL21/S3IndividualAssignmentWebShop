package com.example.demo.controller;

import com.example.demo.business.CreateFootballMatchUseCase;
import com.example.demo.business.DeleteFMatchUseCase;
import com.example.demo.business.GetAllMatchesUseCase;
import com.example.demo.domain.FootballMatch;
import com.example.demo.domain.MatchesRequestsAndResponses.CreateMatchRequest;
import com.example.demo.domain.MatchesRequestsAndResponses.CreateMatchResponse;
import com.example.demo.domain.MatchesRequestsAndResponses.GetAllMatchResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MatchControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CreateFootballMatchUseCase createFootballMatchUseCase;

    @MockBean
    private DeleteFMatchUseCase deleteFMatchUseCase;
    @MockBean
    private GetAllMatchesUseCase getAllMatchesUseCase;


    @Test
    @WithMockUser(username = "Ivan40",roles = {"Admin"})
    void createMatch_shouldReturn201() throws Exception {
        CreateMatchRequest request = CreateMatchRequest.builder()
                .firstTeam("MyTeam")
                .secondTeam("YourTeam")
                .date("12-05-23")
                .build();

        when(createFootballMatchUseCase.createMatch(request)).thenReturn(CreateMatchResponse.builder().id(1L).build());

        mockMvc.perform(post("/matches/create").contentType(APPLICATION_JSON_VALUE)
                .content(
                        """
                               {
                               "firstTeam": "MyTeam",
                                "secondTeam": "YourTeam",
                                "date": "12-05-23"
                                
                                }
                                
                                """



                )).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Content-Type",APPLICATION_JSON_VALUE))
                .andExpect(content().json(
                        """
                      
                                 {"id": 1}
                                 """
                ));

        verify(createFootballMatchUseCase).createMatch(request);


    }

    @Test
    @WithMockUser(username = "Ivan40",roles = {"Admin"})
    void deleteMatch_shouldReturn204() throws Exception{
        mockMvc.perform(delete("/matches/5"))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(deleteFMatchUseCase).deleteFootballMatch(5L);
    }

    @Test
    @WithMockUser(username = "Ivan40",roles = {"Admin"})
    void getAllMatches_shouldReturnListOfMatches() throws Exception {
        FootballMatch match1 = FootballMatch.builder()
                .id(1L)
                .firstTeam("MyTeam")
                .secondTeam("YourTeam")
                .date("18-05-2025")
                .build();

        FootballMatch match2 = FootballMatch.builder()
                .id(2L)
                .firstTeam("OurTeam")
                .secondTeam("YourTeam")
                .date("18-05-2024")
                .build();

        GetAllMatchResponse responseDTO = GetAllMatchResponse.builder()
                .matches(List.of(match1, match2))
                .build();

        when(getAllMatchesUseCase.findAllMatches()).thenReturn(responseDTO);

        mockMvc.perform(get("/matches"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(
                        """
                            {
                            "matches":[
                            {
                            "id": 1,
                            "firstTeam": "MyTeam",
                            "secondTeam": "YourTeam",
                            "date": "18-05-2025"
                            },
                             {
                            "id": 2,
                            "firstTeam": "OurTeam",
                            "secondTeam": "YourTeam",
                            "date": "18-05-2024"
                            }
                            ]
                            
                            }
                            """


                ));



    }

}
