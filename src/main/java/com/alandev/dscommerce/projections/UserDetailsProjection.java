package com.alandev.dscommerce.projections;

public interface UserDetailsProjection {
    String getUsername();
    String getPassword();
    Long getId();
    String getAuthority();

}
