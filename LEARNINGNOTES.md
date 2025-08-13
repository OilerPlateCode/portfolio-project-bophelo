###
Module 2
We have to have all classes in the same package for the DI container to pick them up]
- Records are immutable, saves me form getters and setters - Recond components include toString, equals and hashcodes and Getters
- Can have command line runners to run after the application context has been created or after the application has started
- PostConstruct used on a method that needs to be excecuted afrer DI is done to perform any initialization
- We annotate the classes to put the class in the Application Context. We do that to it in th eSprin DI container
- Field injection - When we use @Autowired on the field itslef. this is discouraged as it makes testing harder as we wont be able to mock out eg an repository
- No need to add an Auto wired annotation if there is a single constuctor it becomes implecit

- You can annotate ResponseStatus at the top of the Request controller
- ResponseStatus.NO_CONTENT = 204