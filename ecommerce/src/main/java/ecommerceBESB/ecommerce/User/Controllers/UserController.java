package ecommerceBESB.ecommerce.User.Controllers;



import java.util.List;

import java.util.UUID;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecommerceBESB.ecommerce.Errors.Exceptions.UserNotFoundException;
import ecommerceBESB.ecommerce.User.User;
import ecommerceBESB.ecommerce.User.Requests.UserLogin;
import ecommerceBESB.ecommerce.User.Requests.UserRegister;
import ecommerceBESB.ecommerce.User.Requests.UserSaveRequest;
import ecommerceBESB.ecommerce.User.Requests.UserUpdateRequest;
import ecommerceBESB.ecommerce.User.Services.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/Api/User")
@Transactional
@Validated
public class UserController {
    private final UserService userService;
    
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> getLogin(@Valid @RequestBody UserLogin userReq){
        if(userService.getLoginUser(userReq)){
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }
        return ResponseEntity.status(HttpStatus.OK).body(false);
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> getRegister(@Valid @RequestBody UserRegister userReq){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.getRegisterUser(userReq));
    }


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) throws UserNotFoundException{
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserByEmail(email));
    }



    @PostMapping
    public ResponseEntity<User> saveUser(@Valid @RequestBody UserSaveRequest userReq){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userReq));
    }



    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @Valid @RequestBody UserUpdateRequest userReq) throws UserNotFoundException{
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.updateUser(id, userReq));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(id));
    }
}
