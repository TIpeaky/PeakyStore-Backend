package com.tipeaky.peakystore.services;

import com.tipeaky.peakystore.exceptions.DuplicatedEntityException;
import com.tipeaky.peakystore.exceptions.EntityNotFoundException;
import com.tipeaky.peakystore.model.dtos.CardDTO;
import com.tipeaky.peakystore.exceptions.UnauthorizedException;
import com.tipeaky.peakystore.model.dtos.NotificationDTO;
import com.tipeaky.peakystore.model.dtos.AddressDTO;
import com.tipeaky.peakystore.model.dtos.UserDTO;
import com.tipeaky.peakystore.model.entities.Card;
import com.tipeaky.peakystore.model.entities.Address;
import com.tipeaky.peakystore.model.entities.Role;
import com.tipeaky.peakystore.model.entities.User;
import com.tipeaky.peakystore.model.forms.*;
import com.tipeaky.peakystore.repositories.AddressRepository;
import com.tipeaky.peakystore.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private EmailService emailService;

    @Autowired
    ModelMapper mapper;

    @Transactional
    public UserDTO saveClient(UserForm userForm) {
        if(userRepository.findByCpf(userForm.getCpf()).isPresent())
            throw new DuplicatedEntityException("Usuário com esse CPF já existe no sistema");

        User user = mapper.map(userForm, User.class);

        user.setRoles(new Role(null, "Client"));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));

        if(user.getNotification() == null)
            user.setNotification(false);

        User savedUser = userRepository.save(user);

        EmailForm emailForm = new EmailForm("TIpeaky", "tipeakyorg@gmail.com", savedUser.getEmail(), "Confirmação de cadastro!", "Obrigado por se cadastrar na PeakyStore!");
        emailService.sendEmail(emailForm);

        return mapper.map(savedUser, UserDTO.class);
    }

    public UserDTO findUserById(UUID userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) throw new EntityNotFoundException("Usuário não encontrado");
        return mapper.map(optionalUser.get(), UserDTO.class);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users= userRepository.findAll();
        if(users.isEmpty()){
            throw new EntityNotFoundException("Usuário não encontrado");
        }



        return users.stream().map(user -> mapper.map(user, UserDTO.class)).toList();
    }
    @Transactional
    public UserDTO saveEmployee(UserForm userForm) {
        if(userRepository.findByCpf(userForm.getCpf()).isPresent())
            throw new DuplicatedEntityException("Funcionario com esse CPF já existe no sistema");

        User user = mapper.map(userForm, User.class);

        user.setRoles(new Role(null, "Employee"));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));

        if(user.getNotification() == null)
            user.setNotification(false);

        User savedUser = userRepository.save(user);
        return mapper.map(savedUser, UserDTO.class);
    }

    public NotificationDTO updateNotification(NotificationForm notificationForm, UUID userId) {
        UserDTO userDto = findUserById(userId);
        userDto.setNotification(notificationForm.getNotification());
        userRepository.save(mapper.map(userDto, User.class));
        return (mapper.map (notificationForm, NotificationDTO.class));
    }



    public CardDTO saveCard(CardForm cardForm, UUID userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()) throw new EntityNotFoundException("Usuário não encontrado");

        Card card = mapper.map(cardForm, Card.class);
        optionalUser.get().getCardList().add(card);


        User user = userRepository.save(optionalUser.get());

        CardDTO cardDTO = mapper.map(card, CardDTO.class);


        return cardDTO;
    }

    public AddressDTO saveAddress(AddressRegisterForm addressForm, UUID userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()) throw new EntityNotFoundException("Usuário não encontrado");

        Address address = mapper.map(addressForm, Address.class);
        optionalUser.get().getAddressList().add(address);

        userRepository.save(optionalUser.get());

        AddressDTO addressDTO = mapper.map(address, AddressDTO.class);
        addressDTO.setUserId(optionalUser.get().getId());
        addressDTO.setUserName(optionalUser.get().getName());
        addressDTO.setUserCpf(optionalUser.get().getCpf());

        return addressDTO;
    }

    public String newPassword(UUID id, NewPasswordForm form) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = mapper.map(optionalUser, User.class);

        LoginForm loginForm = new LoginForm(user.getEmail(), form.getPassword());
        UsernamePasswordAuthenticationToken dadosLogin = loginForm.converter();

        try {
            authManager.authenticate(dadosLogin);
        } catch (AuthenticationException e) {
            throw new UnauthorizedException("Senha Incorreta");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(form.getNewPassword()));
        userRepository.save(user);

        return "Senha Alterada com sucesso";
    }

    public AddressDTO findAddressById(UUID addressId) {
        Optional<Address> optionalAddress = addressRepository.findById(addressId);
        if (optionalAddress.isEmpty()) throw new EntityNotFoundException("Endereço não encontrado");
        return mapper.map(optionalAddress.get(), AddressDTO.class);
    }
}
