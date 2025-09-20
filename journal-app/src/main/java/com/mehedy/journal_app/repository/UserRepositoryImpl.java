package com.mehedy.journal_app.repository;

import com.mehedy.journal_app.entity.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepositoryImpl {

    private final MongoTemplate mongoTemplate;

    public UserRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<User>  getUserForSentimentAnalysis() {

//        // The Query is something like this username="mbappe9" and age greather than equal 20
//        query.addCriteria(Criteria.where("userName").is("mbappe9"));
//        query.addCriteria(Criteria.where("age").gte(20));

        Query query = new Query();
        // This Criteria is like as and operator that means email and sentimentAnalysis
//        query.addCriteria(Criteria.where("email").exists(true));
//        query.addCriteria(Criteria.where("sentimentAnalysis").exists(true));


        // The Following one is work like OR operator

        Criteria criteria = new Criteria();
//        query.addCriteria(criteria.orOperator(Criteria.where("email").exists(true),Criteria.where("sentimentAnalysis").exists(true)));
        query.addCriteria(criteria.orOperator(Criteria.where("email").regex("^[a-zA-Z0-9. _%+-]+@[a-zA-Z0-9")));

        // And is also write in the following way
        query.addCriteria(criteria.andOperator(Criteria.where("email").exists(true),Criteria.where("sentimentAnalysis").exists(true)));

        // For array something is like
        query.addCriteria(Criteria.where("userName").nin("Shanu","Kanu"));
        query.addCriteria(Criteria.where("userName").in("Shanu","Kanu"));
        return mongoTemplate.find(query, User.class);
    }
}
