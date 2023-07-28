# CONTINUACION DEL CURSO COMPLETO DE SPRING/SPRING BOOT

# 4ta Seccion - Formularios Thymeleaf y Data Binding

## 1) Creando un nuevo proyecto para FORM (formularios)

    - Creamos todo el package main del proyecto en start.spring.io
    - Creamos package controller y los metodos handler.
        -> De momento dos metodos: GET para mostrar Formulario y POST para enviar la informacion.

## 2) ACTUALIZACION PARA SPRING > 3.0

    - Despues de la 2.3 se quito el validation.
    - Debemos agregarlo solo para esta parte del curso!

        -> EN POM :

        <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>

## 3) Agregando la vista de FORMULARIO

    - Resources > templates...
    - Construir el POST para que envie los datos del form

## 4) Metodo handler para recibir y procesar los datos enviados

    - Parametros para capturar los datos con @RequestParam desde el controlador

## 5) Creando la clase MODEL del formulario

    - Creamos el MODEL y realizamos desde el controlador con POST
    el correspondiente envio de datos para mostrar despues con el GET.

## 6) Mapeando los campos del formulario al objeto MODEL

    - QUITAREMOS desde el controlador el envio de datos mediante SET y demas para mapearlos desde el objeto MODEL
    - DESDE AQUI enviaremos el usuario como parametro para que quede mas limpio..

            -> public String procesar(Usuario usuario, Model model){..

    - IMPORTANTE QUE EN EL POJO TENGAMOS LOS GET AND SET PARA LOS DATOS DE LOS USUARIOS

## 7) Validacion del Formulario usando anotaciones de la dependencia VALIDATION

    - En el controlador colocaremos @Valid para validar los parametros Usuario usuario dentro del procesamiento de datos

            -> public String procesar(@Valid Usuario usuario, Model model){..

    - LUEGO EN EL MODEL DE USUARIO COLOCAREMOS LAS REGLAS DE VALIDACION QUE QUEREMOS PARA LOS DATOS!
    - Ahora desde el controlador y el POST, validaremos esto E INYECTAMOS COMO PARAMETRO EL BindingResult result..
    - Creamos el MENSAJE de error dentro del controlador

## 8) Agregando los mensajes de errores en la vista del FORMULARIO

    - Desde la vista mostramos los value de los campos que queremos, para que no se reinicien si hay un error y hay que
    refrescar la pagina.
    - Con @ModelAttribute podemos agregar un 'tag' o nuevo nombre para mostrar en la vista con ese nombre.

## 9) El atributo OBJECT the thymeleaf en el elemento FORM

    Desde THYMELEAF
    - Quitando valores en THYMELEAF para automatizar y limpiar codigo dentro del HTML
    - Quitamos 'name' y modificamos th:value a th:field
    - AHORA -> atributo th:object en el inicio del HTML asi todo esta MAPEADO a ese objeto en el thymelef
    - y en FIELD solamente indicamos el nombre del atributo con el * o las llaves

    Desde el CONTROLADOR
    - Podemos automatizara tambien, el mensaje de ERROR que definimos en el controlador. El siguiente codigo, lo vamos a automatizar
            
            Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(err ->{
				errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
			});
			model.addAttribute("error", errores);

    - NUEVO CODIGO en thymeleaf -> fields.hasErrors() y th:errors..

## 10) Mostrar valores de ATRIBUTOS del OBJETO model en el FORMULARIO
        
    - Agregamos NOMBRE y APELLIDO en el MODEL de USUARIO para luego mapearlo
    - Agregamos en el GET request la muestra de usuario y luego lo mapeamos en el THYMELEAF con el object de USERNAME
    - No pasar todos los datos al formulario, ejemplo si en la base de datos hay muchos campos y queremos omitir alguno
    - EJEMPLO: 

            -> Agregmos en el MODEL de usuario, un ID y como no va a estar en el FORM, no lo validamos
            -> Agregamos la info en el controlador pero NO AGREGAMOS EN EL FORM, si en el RESULTADO (muestra de datos)
            -> PROBAMOS Y QUEDA EN NULL! como podemos arreglar esto? COMO PODEMOS PERSISTIR.
                
                RESPUESTA: USANDO SESIONES HTTP! DURA LO QUE DURA LA SESION HTTP!   

## 11) @SessionAttributes para mantener los datos durante el ciclo del FORM

    - Como podemos mantener los datos del objeto ENTITY algun atr que no esta mapeado a usuario, ejemplo guardado en la base datos
    - Cuando enviamos los datos del formulario, este DATO queda NULL ya que no esta mapeado en el form, no hay forma de mandar datos desde alli
    - UTILIZAMOS LA ANOTACION @SessionAttributes("") dentro del controlador
    - AHORA, IMPORTANTE. EN EL POST REQUEST agregamos el SESSIONSTATUS
    - IMPORTANTE para ID'S, y campos de este estilo. Campos que no se modifican a traves de formulario, son propios e internos.

## 12) Anotacion @Size para rangos e @Email para validar correo electronico
    
    - VALIDAMOS email y username (min y max de caracteres) desde el model de USUARIO con @Email y @Size
    - Mensaje PERSONALIZADO para los errores. Dentro de las anotaciones podemos agregar el -> @..(message = "..")

## 13) Mensajes de errores personalizados usando PROPERTIES

    - Agregamos el "messages.properties" y alli agregamos los msj personalizados para cada anotacion

## 14) Archivos properties y acentos en UTF-8

## 15) Validacion personalizada usando @Pattern para EXPRESIONES REGULARES! (IMPORTANTE!)

    - Dentro del MODEL con la anotacion @Pattern(regexp = ...)

## 16) Validacion personalizada usando una clase VALIDATION

    - A traves de un METODO de validacion, nos da mas personalizacion y posibilidades
    - VALIDAREMOS -> Nombre e Identificador (lo comentamos del MODEL) con UsuarioValidator
    - INYECTAMOS en el controlador la nueva clase de validacion
    - CORRECTO! 

## 17) Registrando una clase validador con la anotacion @InitBinder

    - PODEMOS comentar la linea de codigo de validador.validate.. y que el @Valid haga todo el trabajo
        -> REGISTRAR ESTE VALIDADOR EN EL @INITBINDER! 
        -> Si utilizamos el setValidator perdemos todos los validadores de anotaciones de MODEL usuarios
        -> debemos usar addValidators en vez de setValidator!

## 18) Validacion personalizada usando anotaciones
    
    - Similar a anotaciones como @NotEmpty pero mas personalizado, ejemplo para validar RegExp
    - CREAMOS UNA ANOTACION! en validation
    - Implementamos la clase anotacion y la clase de la validacion y agregando el codigo correspondiente.

## 19) Validacion personalizada usando anotaciones ejemplo requerido

    - Realizamos la anotacion de validacion pero para APELLIDO, en vez del @NotEmpty

## 20) Validacion de numeros enteros con @Max, @Min y NotNUll

    - Creamos un nuevo atributo INTEGER para validar -> cuenta.

## 21) Validacion de fechas con @NotNull y dando formato con @DateTimeFormat

    - Ver fechaNacimiento en el model de USUARIO con @DateTimeFormat y el patter + los msj de error

## 22) Validacion de fechas con input de HTML5, @Past and @Future

## 23) Formateando fechas con @InitBinder y registando un CustomDateEditor

    - Con el initBinder del controlador construimos la fecha personalizada, es una opcion de DateTimeFormat

## 24) Formateando fechas con @initBinder y registrando un CustomDateEditor PARTE 2

    - SimpleDateFormat propia de Java no de SPRING, recibe una fecha como string y realiza un parse para convertir el string
    a un objeto DATE de Java
    - Si queremos colocar solo para un atributo especifico -> agregamos el nombre del atributo en el binder (field)

## 25) Implementando un propio filtro custom property editor para convertir a mayusculas (Nombre o Apellido)

    - Creamos un package 'editor' y dentro creamos la clase NombreMayuscularEditor
    - Heredamos de PropertyEditorSupport y sobreescribimos el metodo setAsText para mayuscula
    - Luego registramos en el controlador dentro del binder con la sintaxis anterior (binder.regi...(String.class, "nombre", new Nombre...)

## 26) Lista select desplegable

    - Para seleccionar un ejemplo de una lista. Ejemplo, un atributo PAIS dentro del MODEL para seleccionar el pais del usuario
    - @ModelAttribute | Creamos un metodo en el controlador y lo pdemos usar en cualquier metodo handler del controlador

## 27) Formateando fechas en vistas de thymeleaf

    - <li th:text="${#dates.format(usuario.fechaNacimiento, 'dd/MM/yyyy')}"></li>

## 28) Llenando lista select con Map

    - Poblar el select con el value y el texto es lo que se va a mostrar en el select

## 29) Llenando lista select con objetos de la clase Pais

    - Creamos el model para la clase PAIS y generamos la validacion necesaria junto al @ModelAttribute correspondiente

## 30) Agregando componente Service de Pais
    
    - PARA PODER ENVIAR EL PAIS COMPLETO! no solamente el id o el codigo, enviar todo el objeto
    - Repositorio de PAISES en una sola clase, para no estar pasando una List de paises desde el controlador
    - Agregamos @Service a la clase PaisSer.. para poder inyectar
    - Inyectamos en el controlador y modificamos la implementacion de listarPais

## 31) Agregando componente pais property editor

## 32) Implementando CheckBoxes

    - Roles a los cuales se puede registrar a un usuario

## 33) Llenando CheckBoxes con Map
    
    - Aplicamos el metodo con HashMap como el de paises, igual

## 34) Llenando checkboces con objetos del tipo Rol

    - Mostrando el ROL sin el ROL_ADMIN sino como Nombre ADMINISTRADOR y demas
    - Creamos el MODEL para Rol

## 35) Agregamos componente role propery editor

    - Lo mismo que hicimos para Paises agregamos para Roles. Agregamos el RolesEditor e implementamos todo en el InitBinder
    - Para poder mostrar los roles en resultados

## 36) CheckBox booleano true or false

    - Creamos el @ModelAttribute para genero como hicimos antes para los otros atributos

## 37) Input type hidden
    
    - No se muetra, pero por detras del HTML se realiza
    
## 38) Agregando valores por default a PAIS y a ROLES

## 39) Redirect despues del POST procesar

    - Cuando el usuario refresca el formulario, vuelve a enviar los datos
    - EN UN PROYECTO REAL ESTO TIENE QUE EVITARSE OBLIGATORIAMENTE! ya que duplica en base de datos por ejemplo
    - IMPLEMENTAREMOS ESTO, cuando obtenemos los datos del formulario -> Redirigir a otra pagina, no se vuelve a enviar ya que es otro request
    - en el POSTMAPPING tenemos que EVITAR EL return "resultado"; EL RETURN DE VOLVER A CARGAR LA LISTA!
    - Agregamos un nuevo GETMAPPING y migramos todo para los redirect tanto a ver como al form de vuelta si el usuario es null

## 40) AGREGAMOS CSS AL PROYECTO con BOOTSTRAP!
    
    - FALTA AGREGAR!


# CONTINUACION DEL CURSO COMPLETO DE SPRING/SPRING BOOT

# 2da PARTE - INTERCEPTORES HTTP

## 1) Introduccion 

    - Fragmentos de codigos reutilizables que interceptan un metodo del controlador para agregar funcionalidad

        -> EJEMPLOS: Autenticacion con JWT, Autorizacion, Logging, Transacciones

    - COMO FUNCIONA? : Deben implementar la interfaz HandlerInterceptor o Extender de la clase abstracta HandlerInterceptorAdapter

            ->  boolean preHandler() ; Ejemplo: Autenticacion, Transacciones
            ->  void postHandler()
            ->  void afterCompletion()

    - ES TRANSVERSAL A NUESTRA LOGICA DE NEGOCIO, es solo un APOYO, NO ESTA RELACIONADA A NUESTRA LOGICA DE NEGOCIO!

    - Creamos un INTERCEPTOR para saber cuando tarda en cargar una pagina o nuestra aplicacion en milisegundos ante una request
    
## 2) Implementando el interceptro TiempoTranscurrido
    
    - Ver implementacion en clase TiempoTranscurridoInterceptor

## 3) Implementando Interceptor TiempoTranscurrido

    - Creamos una clase CONFIGURACION de Spring MVC para registrar el interceptor
    - MvcConfig y registramos el interceptor con la Inyeccion de la clase TiempoTranscurridoInterceptor
    
## 4) Implementando fragment interceptor para reutilizar codigo en thymeleaf

    - Creamos un nuevo dir en templates para components
    - creamos un nuevo html para tiempo transcurrido con el parametro dentro del section th:fragment
    - Luego lo importamos dentro del section de form con la sintaxis alli dada

    - Si queremos que se ejecute solo en un metodo GET o distinto de un POST, lo agregamos en el interceptor de tiempo transcurrido

## 5) Retornando false en un interceptor
    
    - Ejemplo para login de usuarios cuando queremos que el controlador continue si el inicio de sesion del usuario es correcto
    - Ya que con false no se ejecuta mas el codigo de la aplicacion.

    - POR EJEMPLO, TENEMOS UN INTERCEPTOR QUE VALIDA LA SESION DE USUARIO, PODEMOS REDIGIR CUANDO NO SE PUEDA VALIDAR LA SESION
    AL LOGIN NUEVAMENTE, LO MOSTRAMOS EN TIEMPOTRANSCURRIDO SOLO COMO MUESTRA YA QUE ESTE INTERCEPTOR NO VALIDA NADA.

# CONTINUAMOS EN EL SIGUIENTE PROYECTO PARA INTERCEPTORES, VALIDACION DE HORARIO!
    