package PRMProject.service;

import PRMProject.entity.Order;
import PRMProject.entity.User;
import PRMProject.model.UserDto;

import java.util.List;

public interface UserService {

    String getRoleByUserNameAndPassword(String username, String password);

    User createUser(User user);

    List<UserDto> getAll(String username, String role, Long skillId, Boolean isDelete,Boolean isMyProfile);

    UserDto getById(Long id);

    List<Order> getOrderByUsername(String username);

    User saveDeviceId(String deviceId);

    void update(Long id, UserDto userDto);

    void addSkillToUser(Long userId, Long[] skillId);

    void removeSkillOfUser(Long userId, Long[] skillId);

    void deleteUser(Long userId);
}
