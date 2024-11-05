package com.eduqa_backend.services;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eduqa_backend.dto.LectureCourseProgressCommentDTO;
import com.eduqa_backend.input.CommentInput;
import com.eduqa_backend.modal.LectureCourseProgressComment;
import com.eduqa_backend.modal.LectureCourseProgressReport;
import com.eduqa_backend.modal.User;
import com.eduqa_backend.repository.LectureCourseProgressCommentRepository;
import com.eduqa_backend.repository.LectureCourseProgressReportRepository;
import com.eduqa_backend.repository.UserRepository;
import java.util.*;
@Service
public class LectureCourseProgressCommentServices {
    @Autowired
    private LectureCourseProgressCommentRepository lectureCourseProgressCommentRepository;
    @Autowired
    private LectureCourseProgressReportRepository lectureCourseProgressReportRepository;
    @Autowired
    private UserRepository userRepository;

    public LectureCourseProgressCommentDTO createCourseProgressComment(CommentInput comment, Principal principal) {
        try {
            LectureCourseProgressReport lcpr = lectureCourseProgressReportRepository
                    .findById(UUID.fromString(comment.getLCourseProgressReportId()))
                    .orElseThrow(() -> new RuntimeException("Please select chapter"));
            User user = userRepository.findByEmail(principal.getName())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            LectureCourseProgressComment saveComment = lectureCourseProgressCommentRepository.save(new LectureCourseProgressComment(comment.getComment(), user, lcpr));
            System.out.println("Identification "+saveComment.getId());
            return new LectureCourseProgressCommentDTO(this.findById(saveComment.getId()));
        } catch (Exception e) {
            return new LectureCourseProgressCommentDTO();
        }
    }

    public LectureCourseProgressComment findById(UUID id){
      return lectureCourseProgressCommentRepository.findById(id).orElseThrow(()->new RuntimeException("Comment not found"));

    }
    public List<LectureCourseProgressCommentDTO> getChapterComments(Principal principal, String lectureCourseProgressId) {
        return lectureCourseProgressCommentRepository.findAllByLectureCourseProgressReportId(UUID.fromString(lectureCourseProgressId)).stream()
                .map(LectureCourseProgressCommentDTO::new).toList();

    }
    public List<LectureCourseProgressCommentDTO> findLatestMessageForEachCourseContentReport(String semesterId){
        return lectureCourseProgressCommentRepository.findLatestMessageForEachCourseContentReport(UUID.fromString(semesterId)).stream().map(LectureCourseProgressCommentDTO::new).toList();
    }
}
