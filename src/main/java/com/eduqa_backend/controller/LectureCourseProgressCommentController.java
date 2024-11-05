package com.eduqa_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.eduqa_backend.dto.LectureCourseProgressCommentDTO;
import com.eduqa_backend.input.CommentInput;
import com.eduqa_backend.services.LectureCourseProgressCommentServices;
import java.security.*;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;

@RestController()
@CrossOrigin
// @RequestMapping("/api/lecture-course-progress-comment")
public class LectureCourseProgressCommentController {
    @Autowired
    private LectureCourseProgressCommentServices lectureCourseProgressCommentServices;
    @Autowired
    private SimpMessageSendingOperations sendingOperations;

    @MessageMapping("/create-message")
    public void createComment(@Payload CommentInput comment, Principal principal) {
        LectureCourseProgressCommentDTO lcpcd = lectureCourseProgressCommentServices
                .createCourseProgressComment(comment, principal);
        sendingOperations.convertAndSendToUser(lcpcd.getLectureCourseContentId(), "/queue/messages", lcpcd);
    }

    @GetMapping("/api/lecture-course-progress-comment/get/chapter-comments/{lectureCourseProgressId}")
    public List<LectureCourseProgressCommentDTO> getChapterComments(Principal principal,
            @PathVariable String lectureCourseProgressId) {
        return lectureCourseProgressCommentServices.getChapterComments(principal, lectureCourseProgressId);
    }
@GetMapping("latest-comment/{semesterId}")
public List<LectureCourseProgressCommentDTO> findLatestMessageForEachCourseContentReport(@PathVariable String semesterId) {
    return lectureCourseProgressCommentServices.findLatestMessageForEachCourseContentReport(semesterId);
}

}
