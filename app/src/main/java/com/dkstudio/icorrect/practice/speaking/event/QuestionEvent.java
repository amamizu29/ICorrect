package com.dkstudio.icorrect.practice.speaking.event;


import com.dkstudio.icorrect.practice.speaking.dto.InteractQuestionDTO;

/**
 * Created by khiemnt on 06/07/2016.
 */
public class QuestionEvent
{
    public static class GetInteractionQuestion
    {
        InteractQuestionDTO interactQuestionDTO;

        public GetInteractionQuestion(InteractQuestionDTO interactQuestionDTO)
        {
            this.interactQuestionDTO = interactQuestionDTO;
        }

        public InteractQuestionDTO getInteractQuestionDTO()
        {
            return interactQuestionDTO;
        }
    }
}
