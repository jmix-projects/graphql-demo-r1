package com.company.graphqldemor1;

import io.jmix.core.security.CurrentAuthentication;
import io.jmix.graphql.service.UserInfo;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@GraphQLApi
@Service("UserInfoGraphQLService")
public class UserInfoGraphQLService {

    @Autowired
    protected CurrentAuthentication currentAuthentication;

    @GraphQLQuery
    public UserInfo getUserInfo() {
        UserDetails user = currentAuthentication.getUser();
        UserInfo userInfo = new UserInfo(user);
        userInfo.setLocale(currentAuthentication.getLocale().toString());
        return userInfo;
    }
}
