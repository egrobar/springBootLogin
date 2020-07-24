package demo;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import demo.db.User;

public class UserDetailsImpl implements UserDetails {

	private static Integer id;

	private String userName;
	private String password;
	

	public UserDetailsImpl(User user) {
		this.userName = user.getUsername();
		this.password = user.getPassword();
		
	}

	public UserDetailsImpl(org.springframework.security.core.userdetails.User user) {}


	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {

		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public boolean isEnabled() {
      // TODO Auto-generated method stub
      return false;
    }

	

}