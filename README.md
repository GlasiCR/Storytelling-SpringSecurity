# Booking Hotel - API 
<div style="display: flex;"> <br>
<img align="center", src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white">
<img align="center", src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
<img align="center", src="https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=Spring-Security&logoColor=white">  
<img align="center", src="https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white">
<img align="center", src="https://img.shields.io/badge/json%20web%20tokens-323330?style=for-the-badge&logo=json-web-tokens&logoColor=pink">
<br>
<br>
</div>

## Storytelling sobre o Spring Security:

1)	Eu preciso de uma ferramenta, o que fazemos normalmente? Compramos e incluimos na maleta de ferramentas certo? rs Pois bem, aqui não vai ser diferente, a primeira coisa que precisamos fazer para usar o Spring Security é adicionar a sua dependência ao nosso arquivo pom.xml, nossa caisa de ferramentas do Java.
Sugiro extrair a dependência direto da fonta assim mitiga eventuais problemas por causa da versão: https://start.spring.io/ >  Seleciona “Project”, “Language”, “Spring Boot” > ADD DEPENDENCIES > Procure por Spring Security > EXPLORE

2)	Agora que tenho uma ferramenta instalada para segurança, claro que ela já vem preparada para assegurar a aplicação. Então se você tinha algum endpoint funcionando, ele não está mais.

3)	Vamos falar para a nossa ferramenta de segurança que eu farei as configurações de segurança conforme necessidade:

``` java
@Configuration
@EnableWebSecurity
public class AuthConfig {
    @Autowired
    AuthFilter authFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return  httpSecurity
                .csrf(csrf -> csrf.disable())
                .build();
    }
```

Depois disso os endpoint devem ter voltado a funcionar normalmente, é como se tivéssemos zerado as configurações de segurança que o Security traz na instalação.

4)	Bom, do que me adianta uma ferramenta de segurança se eu não falar pra ela o que deve ser assegurado, de quem e como ter certeza se quem está acessando pode acessar?
5)	Então vamos começar pelo começo, primeiramente, quando crio um usuário tenho que avisar ao Spring Security os dados da pessoa que pode acessar e quais suas credenciais de acesso. A interface UserDetails é própria dele e tem esse papel nesse processo.
E como ele vem pra facilitar, obrigatoriamente devemos implementar os métodos que vem junto com ele, os quais farão esse reconhecimento das credenciais:

``` java
public class User implements UserDetails {
//(...)
//Estes são os métodos da interface:

//Para as configurações de perfil de acesso. Caso não precise de perfis diferenciados na aplicação, basta deixar “return null”.
@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
   if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
   else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
}

//Indica qual o atributo é a senha do usuário
@Override
public String getPassword() {
    return password;
}

//Indica qual o atributo será o “Username”, ou seja, o login. No meu caso é o e-mail.
@Override
public String getUsername() {
    return email;
}
//Checa se a conta do usuário não expirou
@Override
public boolean isAccountNonExpired() {
    return true;
}
//Verifica se usuário está bloqueado
@Override
public boolean isAccountNonLocked() {
    return true;
}
//Analisa se os dados não expiraram 
@Override
public boolean isCredentialsNonExpired() {
    return true;
}
//Olha se a conta usuário está habilitada
@Override
public boolean isEnabled() {
    return true;
}
//Deixei tudo como true para que em todos estes casos o usuário seja considerado válido, mas dependendo da sua aplicação faça os ajustes necessários 
}
```

6)	Boa. Já temos o molde do meu usuário, inclusive pela ótica Segurança, então vamos para a criação do usuário.
   
7)	Aproveitamos o momento aqui, para usufruir de outra facilidade que o Spring Security traz que é a criptografia da senha sem precisar de nenhuma biblioteca adicional para isso =)
```java
    public ResponseEntity createUser(UserCreateDto user){
  if(userRepository.findByEmail(user.email()) != null){
        return ResponseEntity.badRequest().body("Usuário já existe");
    }
    String passwordEncrypt = new BCryptPasswordEncoder().encode(user.password());
    User newUser = new User(user.name(), user.email(), passwordEncrypt, user.role());
    userRepository.save(newUser);

    return ResponseEntity.ok().body("Usuário cadastrado com sucesso");
}
```

8)	Temos o molde, os pré-requisitos de quem pode acessar e cadastrei o usuário. Chegou a hora de dizer para a minha ferramenta de segurança quais as portas que estão liberadas e quais requer autorização para acessar. Então vamos voltar ao nosso arquivo que zeramos as configurações iniciais do Spring Security e adicionar estas informações:

```java
public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return  httpSecurity
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers(HttpMethod.POST, "/login/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/users/create").permitAll()
                    .requestMatchers(HttpMethod.POST, "/hotels/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/rooms/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
            )
            .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
}
```java

9)	Pronto, a partir de agora o Spring Security interceptará todas as requisições e estas passarão pelo filtro de segurança que acabamos de cadastrar. 
10)	Agora para fechar esse ciclo de segurança de acesso a nossa aplicação, vamos ao login.

11)	Já na camada Controller checo as credencias com o Spring Security.

12)	Primeiramente eu encapsulo em um objeto os dados do login que recebi da requisição, no meu caso email e password:
```java
var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
```

13)	 Agora que tenho o objeto do que o usuário passou, preciso comparar se está de acordo com o que foi armazenado e recuperar o que esse usuário pode acessar ou não.
14)	Para fazer esta conferência e sabermos se o usuário pode ser autenticado ou não, usamos duas coisas que o próprio Security nos oferece. O método, authenticationManager que leva com ele no parâmetro os dados da requisição e a Interface UserServiceDetails, que fará a ponte com o banco de dados. 
O métodos usamos no Controller mesmo, ficando assim o código completo: 
```java
public ResponseEntity login(@RequestBody @Valid AuthDto data){
    var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
    var auth = this.authenticationManager.authenticate(usernamePassword);

    var token = tokenService.generateToken((User) auth.getPrincipal());

    return ResponseEntity.ok(new LoginResponseDto(token));
}
```
15)	Já o UserDetailsService criamos uma classe exclusivamente para implementar este Service:
```java
public class AuthUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username);
    }
}
```

16)	Se o UserDetailsService conseguir encontrar no banco de dados um usuário com os mesmos dados que recebeu da requisição, ele retornará  para o authenticationManager o objeto com as informações desde usuário.


## Funcionalidades da aplicação até o momento:
- Cadastro de usuário;
- Login com validação de senha e token (JWT); 
- Atualização de dados do usuário;
- Cadastro de Hotel;
- Cadastro de Quarto e relacionamento com o Hotel;
- Filtro por nome do hotel e cidade.

<br>
<br>
#### em construção...
