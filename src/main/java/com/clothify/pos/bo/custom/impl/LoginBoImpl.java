package com.clothify.pos.bo.custom.impl;

import com.clothify.pos.bo.custom.LoginBo;
import com.clothify.pos.controller.system_pages.OrderPageFormController;
import com.clothify.pos.dao.DaoFactory;
import com.clothify.pos.dao.custom.LoginDao;
import com.clothify.pos.dto.Login;
import com.clothify.pos.entity.LoginEntity;
import com.clothify.pos.util.DaoType;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import javafx.scene.control.Alert;
import org.modelmapper.ModelMapper;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginBoImpl implements LoginBo {

    ModelMapper mapper = new ModelMapper();

    private final LoginDao loginDao = DaoFactory.getInstance().getDao(DaoType.LOGIN);

    Random random = new Random();
    String otp = random.nextInt(10000)+"";

    @Override
    public Boolean persist(Login login) {
        Map<String, String> map = encryptPassword(login);
        login.setPassword(map.get("EncryptPassword"));
        login.setSaltValue(map.get("SaltValue"));
        return loginDao.persist(mapper.map(login, LoginEntity.class));

    }

    @Override
    public Boolean update(Login login,String otp) {
        Map<String, String> map = encryptPassword(login);
        login.setPassword(map.get("EncryptPassword"));
        login.setSaltValue(map.get("SaltValue"));
        if(Boolean.TRUE.equals(verifyOtp(otp))){
          return loginDao.update(mapper.map(login, LoginEntity.class));
        }
        return false;
    }

    @Override
    public Boolean accountVerify(String email, String password) {
        Login login = mapper.map(loginDao.getLogin(email), Login.class);
        //decrypt has  done
        return  decryptPassword(password,login.getPassword(),login.getSaltValue());
    }

    //Verify Otp when user forget the password

    public Boolean verifyOtp(String userOtp){
        return userOtp.equals(otp);
    }


    @Override
    public Boolean getEmailInfo(String email){

        String emailText = "Your otp for the login is "+otp+". Enter it in the system to verification.";
        try {
            sendEmail(email,emailText);
            return true;
        } catch (MessagingException e) {
            new Alert(Alert.AlertType.ERROR,"Email not relatable with the system");
        }
        return false;

    }


    @Override
    public Map<String,String> getEmailData(String email){
        Login login = mapper.map(loginDao.getLogin(email), Login.class);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("adminType",login.getAdminType());
        hashMap.put("loginId",login.getLoginId());
        hashMap.put("email",login.getEmail());
        return hashMap;
    }


    public Message prepareMessage(Session session, String myEmail, String receiveEmail, String text) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmail));
            message.setRecipients(Message.RecipientType.TO,new InternetAddress[]{
                    new InternetAddress(receiveEmail)
            });
            message.setSubject("OTP CODE");
            message.setText(text);

            return message;
        }catch (Exception e){
            Logger.getLogger(OrderPageFormController.class.getName()).log(Level.SEVERE,null,e);
        }
        return null;
    }


    public void sendEmail(String receiveEmail,String text) throws MessagingException {

        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        String myEmail = "chanithwijekoon@gmail.com";
        String password = "cmzdkemiflfpyois";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmail,password);
            }
        });

        Message message = prepareMessage(session,myEmail,receiveEmail,text);
        Transport.send(message);
    }

    public Map<String,String> encryptPassword(Login login){
        String password = login.getPassword();
        String saltValue = PassBasedEnc.getSaltValue(30);
        String encryptedPassword = PassBasedEnc.generateSecurePassword(password, saltValue);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("EncryptPassword",encryptedPassword);
        hashMap.put("SaltValue",saltValue);
        return hashMap;
    }

    public Boolean decryptPassword(String password,String encryptPassword,String saltValue){
        return PassBasedEnc.verifyUserPassword(password, encryptPassword, saltValue);
    }



   private static class PassBasedEnc{
        private static final Random random = new SecureRandom();
        private static final String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        private static  final int iterations = 10000;
        private static final int keyLength = 256;

        public static String getSaltValue(int length) {
            StringBuilder finalValue = new StringBuilder(length);

            for (int i = 0; i < length; i++)
            {
                finalValue.append(characters.charAt(random.nextInt(characters.length())));
            }

            return new String(finalValue);
        }

        public static byte[] hash(char[] password, byte[] salt) {
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
            Arrays.fill(password, Character.MIN_VALUE);
            try
            {
                SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
                return skf.generateSecret(spec).getEncoded();
            }
            catch (NoSuchAlgorithmException | InvalidKeySpecException e)
            {
                throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
            }
            finally
            {
                spec.clearPassword();
            }
        }

        public static String generateSecurePassword(String password, String salt) {
            String finalValue = null;

            byte[] securePassword = hash(password.toCharArray(), salt.getBytes());

            finalValue = Base64.getEncoder().encodeToString(securePassword);

            return finalValue;
        }

        public static boolean verifyUserPassword(String providedPassword,String securedPassword, String salt) {
            boolean finalValue = false;

            /* Generate New secure password with the same salt */
            String newSecurePassword = generateSecurePassword(providedPassword, salt);

            /* Check if two passwords are equal */
            finalValue = newSecurePassword.equalsIgnoreCase(securedPassword);

            return finalValue;
        }
    }
}
