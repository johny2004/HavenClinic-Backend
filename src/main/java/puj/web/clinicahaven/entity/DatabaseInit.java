package puj.web.clinicahaven.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import jakarta.transaction.Transactional;
import puj.web.clinicahaven.repositorio.RoleRepository;
import puj.web.clinicahaven.repositorio.UserRepository;
import puj.web.clinicahaven.repositorio.VeterinarioRepository;
import puj.web.clinicahaven.repositorio.adminRepository;
import puj.web.clinicahaven.repositorio.clienteRepository;
import puj.web.clinicahaven.repositorio.drogaRepository;
import puj.web.clinicahaven.repositorio.petRepository;
import puj.web.clinicahaven.repositorio.tratamientoRepository;
import puj.web.clinicahaven.servicio.csvService;

@Controller
@Transactional
@Profile({"default", "prod"})
public class DatabaseInit implements ApplicationRunner {

    @Autowired
    clienteRepository clienteRepository1;
    @Autowired
    petRepository petRepository1;
    @Autowired
    VeterinarioRepository veterinarioRepository;
    @Autowired
    adminRepository  adminRepository;
    @Autowired
    drogaRepository drogaRepository;
    @Autowired
    csvService csvService;
    @Autowired
    tratamientoRepository tratamientoRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;
    

    @Override
    public void run(ApplicationArguments args) throws Exception {

        roleRepository.save(new Role("VETERINARIO"));
        roleRepository.save(new Role("CLIENTE"));
        roleRepository.save(new Role("ADMIN"));

        Cliente clienteSave;
        Veterinario veterinarioSave;
        Admin adminSave;
        UserEntity userEntity;

        //CREACION DEL ADMIN
        adminSave = new Admin("user","contra");
        userEntity = saveUserAdmin(adminSave);
        adminSave.setUserEntity(userEntity);
        adminRepository.save(adminSave);

        
        //datos quemados de clientes
        //1.Crear el onbjeto
        //2. Guardar en la tabla User
        //3. Agregar al objeto del paso 1, el ID obtenido en el paso 
        //4. Guardar el objeto en tabla Cliente

        clienteSave = new Cliente("Juan", 123, 1222, "a@c.m", "abc");
        userEntity = saveUserCliente(clienteSave);
        clienteSave.setUserEntity(userEntity);
        clienteRepository1.save(clienteSave);
        clienteSave = new Cliente("Pedro", 124, 1223, "b@c.m", "abc");
        userEntity = saveUserCliente(clienteSave);
        clienteSave.setUserEntity(userEntity);
        clienteRepository1.save(clienteSave);
        clienteSave = new Cliente("Maria", 125, 1224, "c@c.m", "abc");
        userEntity = saveUserCliente(clienteSave);
        clienteSave.setUserEntity(userEntity);
        clienteRepository1.save(clienteSave);
        clienteSave = new Cliente("Luis", 126, 1225, "d@c.m", "abc");
        userEntity = saveUserCliente(clienteSave);
        clienteSave.setUserEntity(userEntity);
        clienteRepository1.save(clienteSave);
        clienteSave = new Cliente("Ana", 127, 1226, "e@c.m", "abc");
        userEntity = saveUserCliente(clienteSave);
        clienteSave.setUserEntity(userEntity);
        clienteRepository1.save(clienteSave);
        clienteSave = new Cliente("Pablo", 1005, 1228, "pq@c.m", "abcd");
        userEntity = saveUserCliente(clienteSave);
        clienteSave.setUserEntity(userEntity);
        clienteRepository1.save(clienteSave);
        clienteSave = new Cliente("Lucas", 12663, 1216, "luc@c.m", "cba");
        userEntity = saveUserCliente(clienteSave);
        clienteSave.setUserEntity(userEntity);
        clienteRepository1.save(clienteSave);
        clienteSave = new Cliente("Johnny", 12666, 1126, "jh@c.m", "bac");
        userEntity = saveUserCliente(clienteSave);
        clienteSave.setUserEntity(userEntity);
        clienteRepository1.save(clienteSave);
        clienteSave = new Cliente("Juan", 123456789, 346123, "juan.perez@example.com", "Segura123!");
        userEntity = saveUserCliente(clienteSave);
        clienteSave.setUserEntity(userEntity);
        clienteRepository1.save(clienteSave);
        clienteSave = new Cliente("Ana", 234567, 634567, "ana.gomez@example.com", "Contraseña456!");
        userEntity = saveUserCliente(clienteSave);
        clienteSave.setUserEntity(userEntity);
        clienteRepository1.save(clienteSave);
        clienteSave = new Cliente("Luis", 345678, 645678, "luis.martinez@example.com", "Password789!");
        userEntity = saveUserCliente(clienteSave);
        clienteSave.setUserEntity(userEntity);
        clienteRepository1.save(clienteSave);
        clienteSave = new Cliente("Maria", 456789, 656789, "maria.lopez@example.com", "Clave101112!");
        userEntity = saveUserCliente(clienteSave);
        clienteSave.setUserEntity(userEntity);
        clienteRepository1.save(clienteSave);
        clienteSave = new Cliente("Pedro", 567890, 667890, "pedro.fernandez@example.com", "Secreta1314!");
        userEntity = saveUserCliente(clienteSave);
        clienteSave.setUserEntity(userEntity);
        clienteRepository1.save(clienteSave);
        clienteSave = new Cliente("Laura", 678901, 678901, "laura.torres@example.com", "Contraseña1516!");
        userEntity = saveUserCliente(clienteSave);
        clienteSave.setUserEntity(userEntity);
        clienteRepository1.save(clienteSave);
        clienteSave = new Cliente("Jorge Ramírez", 78902, 689012, "jorge.ramirez@example.com", "Password1718!");
        userEntity = saveUserCliente(clienteSave);
        clienteSave.setUserEntity(userEntity);
        clienteRepository1.save(clienteSave);
        clienteSave = new Cliente("Carla Sánchez", 80123, 690123, "carla.sanchez@example.com", "Clave1920!");
        userEntity = saveUserCliente(clienteSave);
        clienteSave.setUserEntity(userEntity);
        clienteRepository1.save(clienteSave);
        clienteSave = new Cliente("Fernando Ruiz", 90234, 701234, "fernando.ruiz@example.com", "Secreta2122!");
        userEntity = saveUserCliente(clienteSave);
        clienteSave.setUserEntity(userEntity);
        clienteRepository1.save(clienteSave);
        clienteSave = new Cliente("Sofia Morales", 12346, 712345, "sofia.morales@example.com", "Contraseña2324!");
        userEntity = saveUserCliente(clienteSave);
        clienteSave.setUserEntity(userEntity);
        clienteRepository1.save(clienteSave);
        clienteSave = new Cliente("Hugo Mendoza", 2347, 723456, "hugo.mendoza@example.com", "Password2526!");
        userEntity = saveUserCliente(clienteSave);
        clienteSave.setUserEntity(userEntity);
        clienteRepository1.save(clienteSave);
        clienteSave = new Cliente("Valeria Castro", 5678, 734567, "valeria.castro@example.com", "Clave2728!");
        userEntity = saveUserCliente(clienteSave);
        clienteSave.setUserEntity(userEntity);
        clienteRepository1.save(clienteSave);
        clienteSave = new Cliente("Eduardo Vargas", 4789, 745678, "eduardo.vargas@example.com", "Secreta2930!");
        userEntity = saveUserCliente(clienteSave);
        clienteSave.setUserEntity(userEntity);
        clienteRepository1.save(clienteSave);
        clienteSave = new Cliente("Daniela Soto", 56789, 756789, "daniela.soto@example.com", "Contraseña3132!");
        userEntity = saveUserCliente(clienteSave);
        clienteSave.setUserEntity(userEntity);
        clienteRepository1.save(clienteSave);
        clienteSave = new Cliente("Andrés López", 601, 767890, "andres.lopez@example.com", "Password3334!");
        userEntity = saveUserCliente(clienteSave);
        clienteSave.setUserEntity(userEntity);
        clienteRepository1.save(clienteSave);
        clienteSave = new Cliente("Isabel Gómez", 78902, 778901, "isabel.gomez@example.com", "Clave3536!");
        userEntity = saveUserCliente(clienteSave);
        clienteSave.setUserEntity(userEntity);
        clienteRepository1.save(clienteSave);
        
        clienteSave = new Cliente("Javier Martínez", 0123, 789012, "javier.martinez@example.com", "Secreta3738!");
        userEntity = saveUserCliente(clienteSave);
        clienteSave.setUserEntity(userEntity);
        clienteRepository1.save(clienteSave);
        clienteSave = new Cliente("Natalia Pérez", 934, 790123, "natalia.perez@example.com", "Contraseña3940!");
        userEntity = saveUserCliente(clienteSave);
        clienteSave.setUserEntity(userEntity);
        clienteRepository1.save(clienteSave);
        clienteSave = new Cliente("Óscar Torres", 12456, 801234, "oscar.torres@example.com", "Password4142!");
        userEntity = saveUserCliente(clienteSave);
        clienteSave.setUserEntity(userEntity);
        clienteRepository1.save(clienteSave);
        clienteSave = new Cliente("Camila Rodríguez", 4567, 812345, "camila.rodriguez@example.com", "Clave4344!");
        userEntity = saveUserCliente(clienteSave);
        clienteSave.setUserEntity(userEntity);
        clienteRepository1.save(clienteSave);
        clienteSave = new Cliente("Ricardo Díaz", 35678, 823456, "ricardo.diaz@example.com", "Secreta4546!");
        userEntity = saveUserCliente(clienteSave);
        clienteSave.setUserEntity(userEntity);
        clienteRepository1.save(clienteSave);
        clienteSave = new Cliente("Gabriela Fernández", 6789, 834567, "gabriela.fernandez@example.com", "Contraseña4748!");
        clienteSave = new Cliente("Samuel Hernández", 67890, 845678, "samuel.hernandez@example.com", "Password4950!");
        clienteSave = new Cliente("Paola Morales", 67901, 856789, "paola.morales@example.com", "Clave5152!");
        clienteSave = new Cliente("Rafael García", 7012, 867890, "rafael.garcia@example.com", "Secreta5354!");
        clienteSave = new Cliente("Juliana Silva", 8903, 878901, "juliana.silva@example.com", "Contraseña5556!");
        clienteSave = new Cliente("Alejandro Martínez", 234, 889012, "alejandro.martinez@example.com", "Password5758!");
        clienteSave = new Cliente("Mariana Ruiz", 126, 890123, "mariana.ruiz@example.com", "Clave5960!");
        clienteSave = new Cliente("Luisana Castro", 234567, 901234, "luisana.castro@example.com", "Secreta6162!");
        clienteSave = new Cliente("Sebastián Gómez", 3678, 912345, "sebastian.gomez@example.com", "Contraseña6364!");
        clienteSave = new Cliente("Melissa Rodríguez", 456789, 923456, "melissa.rodriguez@example.com", "Password6566!");
        clienteSave = new Cliente("Felipe González", 50, 934567, "felipe.gonzalez@example.com", "Clave6768!");
        clienteSave = new Cliente("Carolina López", 91, 945678, "carolina.lopez@example.com", "Secreta6970!");
        clienteSave = new Cliente("Alejandra Morales", 78012, 956789, "alejandra.morales@example.com", "Contraseña7172!");
        clienteSave = new Cliente("Guillermo Martínez", 8123, 967890, "guillermo.martinez@example.com", "Password7374!");
        clienteSave = new Cliente("Liliana Pérez", 901230, 978901, "liliana.perez@example.com", "Clave7576!");
        clienteSave = new Cliente("Martha Ramírez", 1234506, 989012, "martha.ramirez@example.com", "Secreta7778!");
        clienteSave = new Cliente("Esteban Torres", 2342567, 990123, "esteban.torres@example.com", "Contraseña7980!");
        clienteSave = new Cliente("Catherine Fernández", 3415678, 100234, "catherine.fernandez@example.com", "Password8182!");
        clienteSave = new Cliente("Héctor Rodríguez", 4563789, 101345, "hector.rodriguez@example.com", "Clave8384!");
        clienteSave = new Cliente("Samantha Gómez", 5678940, 102456, "samantha.gomez@example.com", "Secreta8586!");
        clienteSave = new Cliente("Rodrigo Castro", 6782901, 103567, "rodrigo.castro@example.com", "Contraseña8788!");

        //datos quemados de mascotas
        petRepository1.save(new mascota("Moira", 10, "Labrador Negro", "/Images/pets/perroMoira.jpg", "Hembra",  "displacia de cadera", "Animal de edad mayor; su condicion le causa dolor en la pata trazera derecha"));
        petRepository1.save(new mascota("Trufa", 8, "Labrador Chocolate", "/Images/pets/perroTrufa.jpg", "Hembra", "en recuperacion de cesarea", "El animal dio a luz a 4 cachorros labrador (3 chocolate, 1 negro - todos hembras) por medio de cesarea"));
        petRepository1.save(new mascota("Bill", 4, "Criollo", "/Images/pets/perroBill.jpg", "Macho", "fractura en la pata izquierda trasera", "El animal callejero fue golpeado por un carro; presenta dificultad para caminar"));
        petRepository1.save(new mascota("Joseph", 6, "Labrador Negro", "/Images/pets/perroJoseph.jpg", "Macho" , "herida de mordida cerca del ojo derecho", "El animal fue mordido por uno de sus hermanos mientras jugaban. Presenta secrecion en el ojo e inflamacion en la zona del parpado"));
        petRepository1.save(new mascota("Venus", 1, "Labrador Negro", "/Images/pets/perroVenus.jpg", "Hembra" , "Vomito", ""));
        petRepository1.save(new mascota("Belen", 9, "Labrador Negro", "/Images/pets/perroBelen.jpg", "Hembra", "Ansiedad", ""));
        petRepository1.save(new mascota("Milan", 7, "Labrador Negro", "/Images/pets/perroMilan.jpg", "Macho", "Corneas inflamadas", "Los parpado del perro estan invertidos hacia sus ojos, causandole inflamacion en la cornea por sus pestañas, pero su vision no se ve afectada de forma significante"));
        petRepository1.save(new mascota("Lucia", 6, "Criollo", "/Images/pets/perroLucia.jpg", "Hembra", "Ansiedad", ""));
        petRepository1.save(new mascota("Suco", 8, "Gran Danes", "/Images/pets/perroSuco.jpg", "Macho", "Artritis", "Presenta dolor en la pata trazera izquierda"));
        petRepository1.save(new mascota("Napoleon", 0, "Chihuahua", "/Images/pets/perroNapo.jpg", "Macho", "Recien Nacido", ""));
        
        petRepository1.save(new mascota("Firulais", 5, "Labrador", "", "Macho", "Dolor de pierna", "Dolor de pierna"));
        petRepository1.save(new mascota("Mishi", 4, "Siames", "", "Hembra", "Parasitos", " "));
        petRepository1.save(new mascota("Piolin", 9, "Canario", "" , "Macho", "Ansiedad", " "));
        petRepository1.save(new mascota("Nemo", 7, "Pez Payaso", "", "Macho", "Poco apetito", " "));
        petRepository1.save(new mascota("Daisy", 4, "Gato", "", "Hembra", "Enfermedad Renal", ""));
        petRepository1.save(new mascota("Charlie", 1, "Perro", "", "Macho", "Psitacosis", ""));
        petRepository1.save(new mascota("Oscar", 6, "Perro", "", "Macho", "Displasia de Cadera", ""));
        petRepository1.save(new mascota("Lucy", 8, "Gato", "", "Hembra", "Hipertiroidismo", ""));
        petRepository1.save(new mascota("Bailey", 5, "Perro", "", "Hembra", "Enfermedad Cardíaca", ""));
        petRepository1.save(new mascota("Simba", 2, "Gato", "", "Macho", "Leucemia Felina", ""));
        petRepository1.save(new mascota("Sadie", 4, "Perro", "", "Hembra", "Alergias", ""));
        petRepository1.save(new mascota("Oliver", 3, "Gato", "", "Macho", "Asma", ""));
        petRepository1.save(new mascota("Coco", 7, "Perro", "", "Hembra", "Enfermedad del Pico y Plumas", ""));
        petRepository1.save(new mascota("Buddy", 6, "Perro", "", "Macho", "Epilepsia", ""));
        petRepository1.save(new mascota("Lily", 4, "Gato", "", "Hembra", "Ácaros del Oído", ""));
        petRepository1.save(new mascota("Rocky", 5, "Perro", "", "Macho", "Enfermedad de Cushing", ""));
        petRepository1.save(new mascota("Molly", 3, "Gato", "", "Hembra", "Pancreatitis", ""));
        petRepository1.save(new mascota("Toby", 2, "Perro", "", "Macho", "Parvovirus", ""));
        petRepository1.save(new mascota("Chloe", 6, "Gato", "", "Hembra", "Artritis", ""));
        petRepository1.save(new mascota("Buster", 7, "Perro", "", "Macho", "Enfermedad Hepática", ""));
        petRepository1.save(new mascota("Zoe", 1, "Perro", "", "Hembra", "Retención de Huevos", ""));
        petRepository1.save(new mascota("Jack", 4, "Perro", "", "Macho", "Enfermedad de Lyme", ""));
        petRepository1.save(new mascota("Bella", 3, "Gato", "", "Hembra", "Infección del Tracto Urinario", ""));
        petRepository1.save(new mascota("Duke", 5, "Perro", "", "Macho", "Enfermedad Dental", ""));
        petRepository1.save(new mascota("Misty", 2, "Gato", "", "Hembra", "Conjuntivitis", ""));
        petRepository1.save(new mascota("Rex", 6, "Perro", "", "Macho", "Artritis", ""));
        petRepository1.save(new mascota("Nala", 4, "Gato", "", "Hembra", "Virus de Inmunodeficiencia Felina", ""));
        petRepository1.save(new mascota("Harley", 3, "Perro", "", "Macho", "Obesidad", ""));
        petRepository1.save(new mascota("Mittens", 7, "Gato", "", "Hembra", "Diabetes", ""));
        petRepository1.save(new mascota("Zeus", 5, "Perro", "", "Macho", "Alergias", ""));
        petRepository1.save(new mascota("Luna", 2, "Gato", "", "Hembra", "Tiña", ""));
        petRepository1.save(new mascota("Sam", 6, "Perro", "", "Macho", "Hipotiroidismo", ""));
        petRepository1.save(new mascota("Ruby", 3, "Gato", "", "Hembra", "Infección Respiratoria Superior", ""));
        petRepository1.save(new mascota("Shadow", 4, "Perro", "", "Macho", "Pancreatitis", ""));
        petRepository1.save(new mascota("Ginger", 7, "Gato", "", "Hembra", "Hipertensión", ""));
        petRepository1.save(new mascota("Leo", 5, "Perro", "", "Macho", "Artritis", ""));
        petRepository1.save(new mascota("Sophie", 2, "Gato", "", "Hembra", "Estomatitis", ""));
        petRepository1.save(new mascota("Rocco", 6, "Perro", "", "Macho", "Enfermedad Renal", ""));
        petRepository1.save(new mascota("Lily", 4, "Gato", "", "Hembra", "Hipertiroidismo", ""));
        petRepository1.save(new mascota("Rusty", 7, "Perro", "", "Macho", "Glaucoma", ""));
        petRepository1.save(new mascota("Willow", 3, "Gato", "", "Hembra", "Asma", ""));
        petRepository1.save(new mascota("Gizmo", 5, "Perro", "", "Macho", "Enfermedad de Cushing", ""));
        petRepository1.save(new mascota("Penny", 2, "Gato", "", "Hembra", "Leucemia Felina", ""));
        petRepository1.save(new mascota("Diesel", 6, "Perro", "", "Macho", "Artritis", ""));
        petRepository1.save(new mascota("Sasha", 4, "Gato", "", "Hembra", "Enfermedad Renal", ""));
        petRepository1.save(new mascota("Hunter", 7, "Perro", "", "Macho", "Diabetes", ""));
        petRepository1.save(new mascota("Whiskers", 5, "Gato", "", "Hembra", "Pancreatitis", ""));
        petRepository1.save(new mascota("Bruno", 6, "Perro", "", "Macho", "Enfermedad Cardíaca", ""));
        petRepository1.save(new mascota("Lulu", 3, "Gato", "", "Hembra", "Infección Respiratoria", ""));
        petRepository1.save(new mascota("Thor", 4, "Perro", "", "Macho", "Epilepsia", ""));
        petRepository1.save(new mascota("Mia", 2, "Gato", "", "Hembra", "Conjuntivitis", ""));
        petRepository1.save(new mascota("Jake", 7, "Perro", "", "Macho", "Displasia de Cadera", ""));
        petRepository1.save(new mascota("Poppy", 5, "Gato", "", "Hembra", "Hipertiroidismo", ""));
        petRepository1.save(new mascota("Duke", 3, "Perro", "", "Macho", "Pancreatitis", ""));
        petRepository1.save(new mascota("Molly", 4, "Gato", "", "Hembra", "Infección del Tracto Urinario", ""));
        petRepository1.save(new mascota("Blue", 6, "Perro", "", "Macho", "Enfermedad de Lyme", ""));
        petRepository1.save(new mascota("Gracie", 5, "Gato", "", "Hembra", "Alergias", ""));
        petRepository1.save(new mascota("Bandit", 7, "Perro", "", "Macho", "Enfermedad Hepática", ""));
        petRepository1.save(new mascota("Cleo", 3, "Gato", "", "Hembra", "Asma", ""));
        petRepository1.save(new mascota("Murphy", 4, "Perro", "", "Macho", "Enfermedad Dental", ""));
        petRepository1.save(new mascota("Jasmine", 6, "Gato", "", "Hembra", "Pancreatitis", ""));
        petRepository1.save(new mascota("Bear", 5, "Perro", "", "Macho", "Hipotiroidismo", ""));
        petRepository1.save(new mascota("Lily", 2, "Gato", "", "Hembra", "Tiña", ""));
        petRepository1.save(new mascota("Ace", 7, "Perro", "", "Macho", "Enfermedad Renal", ""));
        petRepository1.save(new mascota("Daisy", 4, "Gato", "", "Hembra", "Hipertensión", ""));
        petRepository1.save(new mascota("Rocky", 6, "Perro", "", "Macho", "Glaucoma", ""));
        petRepository1.save(new mascota("Nina", 3, "Gato", "", "Hembra", "Estomatitis", ""));
        petRepository1.save(new mascota("Ziggy", 5, "Perro", "", "Macho", "Enfermedad de Cushing", ""));
        petRepository1.save(new mascota("Tinker", 2, "Gato", "", "Hembra", "Virus de Inmunodeficiencia Felina", ""));
        petRepository1.save(new mascota("Toby", 7, "Perro", "", "Macho", "Artritis", ""));
        petRepository1.save(new mascota("Misty", 4, "Gato", "", "Hembra", "Infección Respiratoria Superior", ""));
        petRepository1.save(new mascota("Max", 6, "Perro", "", "Macho", "Enfermedad Cardíaca", ""));
        petRepository1.save(new mascota("Kitty", 3, "Gato", "", "Hembra", "Hipertiroidismo", ""));
        petRepository1.save(new mascota("Buddy", 5, "Perro", "", "Macho", "Epilepsia", ""));
        petRepository1.save(new mascota("Lola", 2, "Gato", "", "Hembra", "Enfermedad Renal", ""));
        petRepository1.save(new mascota("Rufus", 7, "Perro", "", "Macho", "Diabetes", ""));
        petRepository1.save(new mascota("Suki", 4, "Gato", "", "Hembra", "Asma", ""));
        petRepository1.save(new mascota("Benny", 6, "Perro", "", "Macho", "Pancreatitis", ""));
        petRepository1.save(new mascota("Princess", 5, "Gato", "", "Hembra", "Conjuntivitis", ""));
        petRepository1.save(new mascota("Rusty", 4, "Perro", "", "Macho", "Infección Bacteriana", ""));
        petRepository1.save(new mascota("Maggie", 3, "Gato", "", "Hembra", "Fiebre", ""));
        petRepository1.save(new mascota("Riley", 6, "Perro", "", "Macho", "Enfermedad Respiratoria", ""));
        petRepository1.save(new mascota("Nico", 2, "Gato", "", "Macho", "Hígado Agrandado", ""));
        petRepository1.save(new mascota("Ellie", 5, "Perro", "", "Hembra", "Colitis", ""));
        petRepository1.save(new mascota("Pepper", 4, "Gato", "", "Macho", "Hipoglucemia", ""));
        petRepository1.save(new mascota("Oscar", 7, "Perro", "", "Macho", "Síndrome de Cushing", ""));
        petRepository1.save(new mascota("Luna", 1, "Gato", "", "Hembra", "Gastroenteritis", ""));
        petRepository1.save(new mascota("Jax", 3, "Perro", "", "Macho", "Infección Ocular", ""));
        petRepository1.save(new mascota("Mimi",6, "Gato", "", "Hembra", "Anemia", ""));
        petRepository1.save(new mascota("Mimi", 6, "Gato", "", "Hembra", "Anemia", ""));
        petRepository1.save(new mascota("Teddy", 5, "Perro", "", "Macho", "Problemas Renales", ""));
        petRepository1.save(new mascota("Ginger", 2, "Gato", "", "Hembra", "Dermatitis", ""));

        //Datos quemados de veterinarios

        veterinarioSave = new Veterinario(1234, "PEDRITO", 111, "Cardilogia", "abc", "", 0, "qwe@m.c");
        userEntity = saveUserVeterinario(veterinarioSave);
        veterinarioSave.setUserEntity(userEntity);
        veterinarioRepository.save(veterinarioSave);
        veterinarioSave = new Veterinario(0301, "Juan Pérez", 1234, "Cirugia", "vetpass1", " ", 0, "juan.perez@vetclinic.com");
        userEntity = saveUserVeterinario(veterinarioSave);
        veterinarioSave.setUserEntity(userEntity);
        veterinarioRepository.save(veterinarioSave);
        veterinarioSave = new Veterinario(1103, "Carlos Ramírez", 3456, "Cardilogia", "vetpass3", " ", 0, "carlos.ramirez@vetclinic.com");
        userEntity = saveUserVeterinario(veterinarioSave);
        veterinarioSave.setUserEntity(userEntity);
        veterinarioRepository.save(veterinarioSave);
        veterinarioSave = new Veterinario(4004, "Ana González", 4567, "Nutricion", "vetpass4", " ", 0, "ana.gonzalez@vetclinic.com");
        userEntity = saveUserVeterinario(veterinarioSave);
        veterinarioSave.setUserEntity(userEntity);
        veterinarioRepository.save(veterinarioSave);
        veterinarioSave = new Veterinario(0065, "Luis Fernández", 5678, "Dentista", "vetpass5", " ", 0, "luis.fernandez@vetclinic.com");
        userEntity = saveUserVeterinario(veterinarioSave);
        veterinarioSave.setUserEntity(userEntity);
        veterinarioRepository.save(veterinarioSave);
        veterinarioSave = new Veterinario(0406, "Laura Martínez", 6789, "Oftamologia", "vetpass6", " ", 0, "laura.martinez@vetclinic.com");
        userEntity = saveUserVeterinario(veterinarioSave);
        veterinarioSave.setUserEntity(userEntity);
        veterinarioRepository.save(veterinarioSave);
        veterinarioSave = new Veterinario(3007, "José Sánchez", 7890, "Cirugia", "vetpass7", " ", 0, "jose.sanchez@vetclinic.com");
        userEntity = saveUserVeterinario(veterinarioSave);
        veterinarioSave.setUserEntity(userEntity);
        veterinarioRepository.save(veterinarioSave);
        veterinarioSave = new Veterinario(5010, "Marta Ruiz", 0123, "Nutricion", "vetpass10", " ", 0, "marta.ruiz@vetclinic.com");
        veterinarioSave = new Veterinario(6011, "Fernando Morales", 1235, "Dentista", "vetpass11", " ", 0, "fernando.morales@vetclinic.com");
        veterinarioSave = new Veterinario(0412, "Patricia Soto", 2346, "Oftamologia", "vetpass12", " ", 0, "patricia.soto@vetclinic.com");
        veterinarioSave = new Veterinario(2213, "Andrés Gil", 3457, "Cirugia", "vetpass13", " ", 0, "andres.gil@vetclinic.com");
        veterinarioSave = new Veterinario(1114, "Cristina Núñez", 4568, "Dermatologia", "vetpass14", " ", 0, "cristina.nunez@vetclinic.com");
        veterinarioSave = new Veterinario(0315, "Miguel Herrera", 5679, "Cardilogia", "vetpass15", " ", 0, "miguel.herrera@vetclinic.com");
        veterinarioSave = new Veterinario(4416, "Sofía Flores", 6780, "Nutricion", "vetpass16", " ", 0, "sofia.flores@vetclinic.com");
        veterinarioSave = new Veterinario(0717, "Víctor Delgado", 7891, "Dentista", "vetpass17", " ", 0, "victor.delgado@vetclinic.com");
        veterinarioSave = new Veterinario(8820, "Claudia Ríos", 0124, "Dermatologia", "vetpass20", " ", 0, "claudia.rios@vetclinic.com");
        veterinarioSave = new Veterinario(3021, "Daniel Ortega", 1236, "Cirugia", "vetpass21", " ", 0, "daniel.ortega@vetclinic.com");
        veterinarioSave = new Veterinario(0422, "Lucía Vargas", 2347, "Dermatologia", "vetpass22", " ", 0, "lucia.vargas@vetclinic.com");
        veterinarioSave = new Veterinario(6023, "Roberto Salinas", 3458, "Cardilogia", "vetpass23", " ", 0, "roberto.salinas@vetclinic.com");
        veterinarioSave = new Veterinario(7024, "Verónica Paredes", 4569, "Nutricion", "vetpass24", " ", 0, "veronica.paredes@vetclinic.com");

        //Datos de drogas
        csvService.uploadCsv("src/main/resources/MEDICAMENTOS_VETERINARIA.csv");

        //datos de tratamiento
        tratamientoRepository.save(new Tratamiento(LocalDate.parse("2024-11-09")));
        tratamientoRepository.save(new Tratamiento(LocalDate.parse("2021-01-12")));
        tratamientoRepository.save(new Tratamiento(LocalDate.parse("2022-12-20")));
        tratamientoRepository.save(new Tratamiento(LocalDate.parse("2022-10-21")));
        tratamientoRepository.save(new Tratamiento(LocalDate.parse("2023-09-19")));
        tratamientoRepository.save(new Tratamiento(LocalDate.parse("2020-01-23")));
        tratamientoRepository.save(new Tratamiento(LocalDate.parse("2019-08-14")));
        tratamientoRepository.save(new Tratamiento(LocalDate.parse("2022-11-06")));
        tratamientoRepository.save(new Tratamiento(LocalDate.parse("2024-09-06")));
        tratamientoRepository.save(new Tratamiento(LocalDate.parse("2024-02-02")));

        
        tratamientoRepository.save(new Tratamiento.TratamientoBuilder().fecha(LocalDate.parse("2024-11-09")).build());
        tratamientoRepository.save(new Tratamiento.TratamientoBuilder().fecha(LocalDate.parse("2021-01-12")).build());
        tratamientoRepository.save(new Tratamiento.TratamientoBuilder().fecha(LocalDate.parse("2022-12-20")).build());
        tratamientoRepository.save(new Tratamiento.TratamientoBuilder().fecha(LocalDate.parse("2022-10-21")).build());
        tratamientoRepository.save(new Tratamiento.TratamientoBuilder().fecha(LocalDate.parse("2023-09-19")).build());
        tratamientoRepository.save(new Tratamiento.TratamientoBuilder().fecha(LocalDate.parse("2020-01-23")).build());
        tratamientoRepository.save(new Tratamiento.TratamientoBuilder().fecha(LocalDate.parse("2019-08-14")).build());
        tratamientoRepository.save(new Tratamiento.TratamientoBuilder().fecha(LocalDate.parse("2022-11-06")).build());
        tratamientoRepository.save(new Tratamiento.TratamientoBuilder().fecha(LocalDate.parse("2024-09-06")).build());
        tratamientoRepository.save(new Tratamiento.TratamientoBuilder().fecha(LocalDate.parse("2024-10-02")).build());
 

        // Asociar mascotas a dueños ciclicamente
        List<mascota> mascotas = petRepository1.findAll();
        List<Cliente> clientes = clienteRepository1.findAll();
        List<Droga> drogas = drogaRepository.findAll();
        List<Tratamiento> tratamientos = tratamientoRepository.findAll();
        List<Veterinario> veterinarios = veterinarioRepository.findAll();

        clientes.removeIf(cliente -> cliente.getCedula() == 1005);

        Cliente P = clienteRepository1.findByCedula(1005);
        for (int j = 0; j < 10; j++){
            mascotas.get(j).setDueño(P);
        }

        for (int i = 10; i < mascotas.size(); i++) {
            mascotas.get(i).setDueño(clientes.get(i % clientes.size()));
            petRepository1.save(mascotas.get(i));
        }

        //Asignar tratamientos con una mascota, veterinario y una droga
        for (int i = 0; i < tratamientos.size(); i++) {
            Tratamiento tratamiento = tratamientos.get(i);

            tratamiento.setMascota(mascotas.get(i % mascotas.size()));
            mascotas.get(i % mascotas.size()).setEnTratamiento(true);

            tratamiento.setVeterinario(veterinarios.get(i % veterinarios.size()));

            tratamiento.setDroga(drogas.get(i % drogas.size()));
            drogas.get(i % drogas.size()).setUnidadesDisponibles(drogas.get(i % drogas.size()).getUnidadesDisponibles() - 1);
            drogas.get(i % drogas.size()).setUnidadesVendidas(drogas.get(i % drogas.size()).getUnidadesVendidas() + 1);

            tratamientoRepository.save(tratamiento);
        }
        
        for (int i = 0; i < mascotas.size(); i++) {
            if(mascotas.get(i).isEnTratamiento()){
                List<Tratamiento> listaTratamientos = new ArrayList<>();
                listaTratamientos.add(tratamientos.get(i % tratamientos.size()));
                mascotas.get(i).setTratamiento(listaTratamientos);
            }
            petRepository1.save(mascotas.get(i));
        }


    }
    
    private UserEntity saveUserCliente(Cliente cliente){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(cliente.getCorreo());
        userEntity.setContrasena(passwordEncoder.encode(cliente.getContrasena()));

        Role roles = roleRepository.findByName("CLIENTE").get();
        userEntity.setRoles(List.of(roles));
        return userRepository.save(userEntity);

        }

    private UserEntity saveUserVeterinario(Veterinario veterinario){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(veterinario.getCorreo());
        userEntity.setContrasena(passwordEncoder.encode(veterinario.getContrasena()));

        Role roles = roleRepository.findByName("VETERINARIO").get();
        userEntity.setRoles(List.of(roles));
        return userRepository.save(userEntity);
    }

    private UserEntity saveUserAdmin(Admin admin){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(admin.getCorreo());
        userEntity.setContrasena(passwordEncoder.encode(admin.getContrasena()));

        Role roles = roleRepository.findByName("ADMIN").get();
        userEntity.setRoles(List.of(roles));
        return userRepository.save(userEntity);
    }



}
