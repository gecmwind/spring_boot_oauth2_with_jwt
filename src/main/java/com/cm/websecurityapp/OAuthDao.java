package com.cm.websecurityapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class OAuthDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserEntity getUserDetails(String username) {
        Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
        String userSQLQuery = "SELECT * FROM USERS WHERE USERNAME=?";
//        List<UserEntity> list = jdbcTemplate.query(userSQLQuery, new String[] { username },
//                (ResultSet rs, int rowNum) -> {
//
//                    UserEntity user = new UserEntity();
//                    user.setUsername(username);
//                    user.setPassword(rs.getString("PASSWORD"));
//                    return user;
//                });
        List<UserEntity> list = new ArrayList<>();
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("myemail@gmail.com");
        userEntity.setPassword("$2a$10$hQrLe/YgeGodhmOqXdw06.RYoeiQvvxkWpzCRfdsW94Qatot1J8yi");
        userEntity.setPhoto("kjdfoiuhun13ounincandcnlaieuriquncoafnou");
        list.add(userEntity);
        if (list.size() > 0) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_SYSTEMADMIN");
            grantedAuthoritiesList.add(grantedAuthority);
            list.get(0).setGrantedAuthoritiesList(grantedAuthoritiesList);
            return list.get(0);
        }
        return null;
    }
}
