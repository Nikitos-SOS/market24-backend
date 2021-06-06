package com.market.marketplace.services;

import com.market.marketplace.models.Comment;
import com.market.marketplace.repositories.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepo commentRepo;

    @Autowired
    public CommentService(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    public Comment addComment(Comment comment){
        return commentRepo.save(comment);
    }

    public List<Comment> findAllComments(){
        return commentRepo.findAll();
    }

    public Comment updateComment(Comment comment){
        return commentRepo.save(comment);
    }

    public void deleteComment(Long id){
        commentRepo.deleteCommentById(id);
    }

    public List<Comment> getProductComments(Long productId){
        List<Comment> comments = commentRepo.findAll();
        List<Comment> resultComments = new LinkedList<>();
        for (Comment comment : comments){
            if(productId.equals(comment.getProductID()))
                resultComments.add(comment);
        }
        return resultComments;
    }
}
