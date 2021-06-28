package com.company.graphqldemor1;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties(prefix = "graphql.demo")
@ConstructorBinding
public class GraphQLDemoProperties {
    private final String admin;
    private final String password;

    public GraphQLDemoProperties(
            @DefaultValue("admin") String admin,
            @DefaultValue("{noop}password") String password) {
        this.admin = admin;
        this.password = password;
    }

    public String getAdmin() {
        return admin;
    }

    public String getPassword() {
        return password;
    }
}
