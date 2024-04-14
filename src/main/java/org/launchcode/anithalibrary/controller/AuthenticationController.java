package org.launchcode.anithalibrary.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.launchcode.anithalibrary.data.RoleRepository;
import org.launchcode.anithalibrary.data.UserRepository;
import org.launchcode.anithalibrary.model.Role;
import org.launchcode.anithalibrary.model.User;
import org.launchcode.anithalibrary.model.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.data.repository.query.Param;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Random;
import org.springframework.mail.javamail.MimeMessageHelper;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Controller
public class AuthenticationController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    //interface for encoding password it impl Bcrypt password encoder
    @Autowired
    private PasswordEncoder passwordEncoder;

    //interface for sending email to user
    @Autowired
    private JavaMailSender mailSender;


    // handler method to handle home page request

    @GetMapping("/index")
    public String home(){

        return "user/index";
    }

    @GetMapping("/")
    public String defaultHome(){

        return "user/index";
    }


    // handler method to handle login request

    @GetMapping("/login")
    public String login(){

        return "user/login";
    }

    // handler method to handle landing page request

    @GetMapping("/landing")
    public String landing(){
        return "user/homepage";
    }

    // handler method to handle user registration form request

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // creates model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "user/register";
    }

    // handler method to handle user registration form submit request

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model){
        //check if user email is not registered already

        User userEmailCheck = userRepository.findByEmail(userDto.getEmail());
        if(userEmailCheck != null && userEmailCheck.getEmail() != null && !userEmailCheck.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        //check if user username is not registered already

        User userNameCheck = userRepository.findByUserName(userDto.getUserName());
        if(userNameCheck != null && userNameCheck.getUserName() != null && !userNameCheck.getUserName().isEmpty()){
            result.rejectValue("userName", null,
                    "Username is already taken");
        }
        //checks error goes back to register page

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "user/register";
        }

        //this save all details to user

        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setUserName(userDto.getUserName());

        // encrypt the password using spring security

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = null;
        role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null){
            role = checkRoleExist("ROLE_ADMIN");
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
        return "redirect:/register?success";
    }

    //checks role and save
    private Role checkRoleExist(String roleName){
        Role role = new Role();
        role.setName(roleName);
        return roleRepository.save(role);
    }

    // handler method to handle forgot password form request

    @GetMapping("/forgot_password")
    public String showForgotPasswordForm() {

        return "user/forgot_password";
    }

    // handler method to handle forgot password form submit request

    @PostMapping("/forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String token = generateRandom();
        try {
            User user = userRepository.findByEmail(email);
            if (user != null) {
                user.setResetPasswordToken(token);
                userRepository.save(user);
                String resetPasswordLink = "http://localhost:8080/reset_password?token=" + token;
                sendEmail(user.getEmail(), resetPasswordLink);
                model.addAttribute("message", "We have sent a reset password link to your email. Please check.");
            }else{
                model.addAttribute("message", "If the username exists in our system, reset link will be sent to your registered email");
            }

        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
        }
        return "user/forgot_password";
    }


    // Method to generate token

    public String generateRandom() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    //  method to send email

    public void sendEmail(String recipientEmail, String link)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("contact@learnerslibrary.com", "Learner's Library");
        helper.setTo(recipientEmail);

        String subject = "Here's the link to reset your password";

        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);
    }

    // handler method to handle Reset password form request

    @GetMapping("/reset_password")
    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
        //UserDto user = userService.getByResetPasswordToken(token);
        User user = userRepository.findByResetPasswordToken(token);
        model.addAttribute("token", token);
        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        }
        return "user/reset_password";
    }

    // handler method to handle Reset password form submit request

    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String newPassword = request.getParameter("password");
        User user = userRepository.findByResetPasswordToken(token);
        model.addAttribute("title", "Reset your password");
        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        } else {
            //userRepository.updatePassword(user, password);
            //User user = userRepository.findByUserName(user.getUserName());
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(newPassword);
            user.setPassword(encodedPassword);
            user.setResetPasswordToken(null);
            userRepository.save(user);
            model.addAttribute("message", "You have successfully changed your password.");
        }
        return "redirect:/login";
    }


    /*public String generateRandom() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }*/

    // handler method to handle list of users
    /*@GetMapping("/users")
    public String users(Model model){
        //List<UserDto> users = userRepository.findAllUsers();

        List<User> users = userRepository.findAll();
        List<UserDto> userDtoList = users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());

        model.addAttribute("users", userDtoList);
        return "users";
    }

    private UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        String[] str = user.getName().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());
        userDto.setUserName(user.getUserName());
        return userDto;
    }*/
}







