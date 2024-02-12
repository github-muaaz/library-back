package com.example.libraryback.component;

import com.example.libraryback.entity.*;
import com.example.libraryback.entity.enums.PermissionEnum;
import com.example.libraryback.entity.enums.RoleEnum;
import com.example.libraryback.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final WebsiteRepository websiteRepository;
    private final FileRepository fileRepository;


    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlMode;

    private final String adminPassword = "123";
    private final String adminUsername = "admin@admin.com";

    private final String superAdminPassword = "123";
    private final String superAdminUsername = "superAdmin@admin.com";

    @Override
    public void run(String... args) {

        if (Objects.equals(ddlMode, "create")) {
            System.out.println("\n\n\n\n\n\n\n\nyou are running with CREATE ddl mode");
            System.out.println("enter 12 to continue");
            Scanner scanner = new Scanner(System.in);

            int input = scanner.nextInt();

            if (!Objects.equals(input, 12))
                System.exit(1);
        }


        if (Objects.equals(ddlMode, "create")) {

            Role roleSuperAdmin = Role
                    .builder()
                    .description("SUPER ADMIN")
                    .name(RoleEnum.SUPER_ROLE_ADMIN.name())
                    .permissions(Set.of(PermissionEnum.values()))
                    .build();

            roleRepository.save(roleSuperAdmin);


            Role roleADMIN = Role
                    .builder()
                    .description("ADMIN")
                    .name(RoleEnum.ROLE_ADMIN.name())
                    .permissions(Set.of(PermissionEnum.ADD_BOOK))
                    .build();

            roleRepository.save(roleADMIN);

            FileImg fileImg = fileRepository.save(
                    FileImg
                            .builder()
                            .name("super admin avatar")
                            .path("/Users/user/IdeaProjects/library/files/img/staticWoman.png")
                            .build()
            );

            User superAdmin = User
                    .builder()
                    .enabled(true)
                    .lastname("Admin")
                    .firstname("Super")
                    .role(roleSuperAdmin)
                    .email(superAdminUsername)
                    .password(passwordEncoder.encode(superAdminPassword))
                    .avatar(fileImg)
                    .build();

            userRepository.save(superAdmin);

            fileImg = fileRepository.save(
                    FileImg
                            .builder()
                            .name("admin avatar")
                            .path("/Users/user/IdeaProjects/library/files/img/staticMan.png")
                            .build()
            );

            User admin = User
                    .builder()
                    .enabled(true)
                    .role(roleADMIN)
                    .lastname("Just")
                    .avatar(fileImg)
                    .firstname("Admin")
                    .email(adminUsername)
                    .password(passwordEncoder.encode(adminPassword))
                    .build();

            userRepository.save(admin);

            fileImg = fileRepository.save(
                    FileImg
                            .builder()
                            .name("logo")
                            .path("src/main/resources/logo.png")
                            .build());

            Website website = Website
                    .builder()
                    .name("Bookoe")
                    .subtitle("Book Store Website")
                    .logo(fileImg)
                    .address("832 Thompson Drive, San Fransisco CA 94107, United States")
                    .phone("+123 345123 556")
                    .webpages(webPages())
                    .location(List.of(12.12321, 22.12313))
                    .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore ")
                    .email("support@bookoe.id")
                    .build();

            websiteRepository.save(website);


            // all after this temporary data

            saveOthers();

        }
    }

    private List<WebPages> webPages() {
        return List.of(
                WebPages
                        .builder()
                        .name("facebook")
                        .image(fileRepository.save(FileImg
                                .builder()
                                .path("/Users/user/IdeaProjects/library/files/img/facebook.svg")
                                .name("facebook")
                                .build()
                        ))
                        .link("http://facebook.com")
                        .build(),
                WebPages
                        .builder()
                        .name("instagram")
                        .image(fileRepository.save(FileImg
                                .builder()
                                .path("/Users/user/IdeaProjects/library/files/img/instagram.svg")
                                .name("instagram")
                                .build()
                        ))
                        .link("http://instagram.com")
                        .build(),
                WebPages
                        .builder()
                        .name("youtube")
                        .image(fileRepository.save(FileImg
                                .builder()
                                .path("/Users/user/IdeaProjects/library/files/img/youtube.svg")
                                .name("youtube")
                                .build()
                        ))
                        .link("http://youtube.com")
                        .build(),
                WebPages
                        .builder()
                        .name("twitter")
                        .image(fileRepository.save(FileImg
                                .builder()
                                .path("/Users/user/IdeaProjects/library/files/img/twitter.svg")
                                .name("twitter")
                                .build()
                        ))
                        .link("http://twitter.com")
                        .build(),
                WebPages
                        .builder()
                        .name("linkedIn")
                        .image(fileRepository.save(FileImg
                                .builder()
                                .path("/Users/user/IdeaProjects/library/files/img/linkedIn.svg")
                                .name("linkedIn")
                                .build()
                        ))
                        .link("http://linkedIn.com")
                        .build()
        );
    }

    // all after this are temporary data

    private final GenreRepository genreRepository;
    private final PromotionRepository promotionRepository;
    private final RecommendationRepository recommendationRepository;
    private final RecommendationBookRepository recommendationBookRepository;
    private final BookRepository bookRepository;
    private final DiscountRepository discountRepository;
    private final BookGenreRepository bookGenreRepository;

         List<Genre> genres = new ArrayList<>();
         List<Book> books = new ArrayList<>();

    List<Discount> discounts = new ArrayList<>();

    List<User> users = new ArrayList<>();
    private void saveOthers() {


        genres();

        discounts = discountRepository.saveAll(List.of(
                Discount.builder().description(lorem).value(28F).build(),
                Discount.builder().description(lorem).value(30F).build(),
                Discount.builder().description(lorem).value(10F).build(),
                Discount.builder().description(lorem).value(25F).build(),
                Discount.builder().description(lorem).value(32F).build(),
                Discount.builder().description(lorem).value(20F).build(),
                Discount.builder().description(lorem).value(50F).build(),
                Discount.builder().description(lorem).value(35F).build(),
                Discount.builder().description(lorem).value(11F).build()
        ));

        books(books);

        promotion();

        bookGenre(genres, books);

        recommendationBooks(books);

        offers();

        featured();

        users();

        feedback();

        news();

        reviews();
    }

    private void reviews() {
        reviewRepository.saveAll(
                List.of(
                        Review.builder().book(books.get(0)).date(new Date(2456543)).message(lorem).point(3).user(users.get(0)).build(),
                        Review.builder().book(books.get(0)).date(new Date(2456543)).message(lorem).point(5).user(users.get(1)).build(),
                        Review.builder().book(books.get(0)).date(new Date(2456543)).message(lorem).point(5).user(users.get(2)).build(),
                        Review.builder().book(books.get(0)).date(new Date(2456543)).message(lorem).point(4).user(users.get(3)).build(),
                        Review.builder().book(books.get(0)).date(new Date(2456543)).message(lorem).point(3).user(users.get(4)).build(),
                        Review.builder().book(books.get(1)).date(new Date(2456543)).message(lorem).point(5).user(users.get(0)).build(),
                        Review.builder().book(books.get(1)).date(new Date(2456543)).message(lorem).point(5).user(users.get(1)).build(),
                        Review.builder().book(books.get(1)).date(new Date(2456543)).message(lorem).point(3).user(users.get(2)).build(),
                        Review.builder().book(books.get(1)).date(new Date(2456543)).message(lorem).point(2).user(users.get(3)).build(),
                        Review.builder().book(books.get(1)).date(new Date(2456543)).message(lorem).point(2).user(users.get(4)).build(),
                        Review.builder().book(books.get(2)).date(new Date(2456543)).message(lorem).point(4).user(users.get(0)).build(),
                        Review.builder().book(books.get(2)).date(new Date(2456543)).message(lorem).point(4).user(users.get(4)).build(),
                        Review.builder().book(books.get(2)).date(new Date(2456543)).message(lorem).point(1).user(users.get(1)).build(),
                        Review.builder().book(books.get(3)).date(new Date(2456543)).message(lorem).point(1).user(users.get(4)).build(),
                        Review.builder().book(books.get(3)).date(new Date(2456543)).message(lorem).point(1).user(users.get(0)).build(),
                        Review.builder().book(books.get(3)).date(new Date(2456543)).message(lorem).point(3).user(users.get(2)).build(),
                        Review.builder().book(books.get(3)).date(new Date(2456543)).message(lorem).point(4).user(users.get(3)).build(),
                        Review.builder().book(books.get(4)).date(new Date(2456543)).message(lorem).point(5).user(users.get(0)).build(),
                        Review.builder().book(books.get(4)).date(new Date(2456543)).message(lorem).point(5).user(users.get(4)).build(),
                        Review.builder().book(books.get(4)).date(new Date(2456543)).message(lorem).point(5).user(users.get(1)).build(),
                        Review.builder().book(books.get(4)).date(new Date(2456543)).message(lorem).point(5).user(users.get(2)).build(),
                        Review.builder().book(books.get(5)).date(new Date(2456543)).message(lorem).point(4).user(users.get(4)).build(),
                        Review.builder().book(books.get(5)).date(new Date(2456543)).message(lorem).point(4).user(users.get(3)).build(),
                        Review.builder().book(books.get(5)).date(new Date(2456543)).message(lorem).point(3).user(users.get(2)).build(),
                        Review.builder().book(books.get(6)).date(new Date(2456543)).message(lorem).point(5).user(users.get(4)).build(),
                        Review.builder().book(books.get(6)).date(new Date(2456543)).message(lorem).point(3).user(users.get(3)).build(),
                        Review.builder().book(books.get(6)).date(new Date(2456543)).message(lorem).point(5).user(users.get(2)).build(),
                        Review.builder().book(books.get(6)).date(new Date(2456543)).message(lorem).point(5).user(users.get(1)).build(),
                        Review.builder().book(books.get(6)).date(new Date(2456543)).message(lorem).point(4).user(users.get(0)).build(),
                        Review.builder().book(books.get(7)).date(new Date(2456543)).message(lorem).point(4).user(users.get(0)).build(),
                        Review.builder().book(books.get(8)).date(new Date(2456543)).message(lorem).point(4).user(users.get(4)).build(),
                        Review.builder().book(books.get(8)).date(new Date(2456543)).message(lorem).point(3).user(users.get(3)).build(),
                        Review.builder().book(books.get(8)).date(new Date(2456543)).message(lorem).point(5).user(users.get(0)).build()
                )
        );
    }

    private void news() {
        newsRepository.saveAll(
                List.of(
                        News.builder().author(users.get(4)).date(new Date(123454)).image(fileRepository.save(
                                FileImg.builder().name("news 1").path("/Users/user/IdeaProjects/library/files/img/1687073309921-Screenshot 2023-06-18 at 12.19.21.png").build()))
                                .title("Why reading is important for our children?").text(lorem).build(),
                        News.builder().author(users.get(4)).date(new Date(122454)).image(fileRepository.save(
                                        FileImg.builder().name("news 1").path("/Users/user/IdeaProjects/library/files/img/1687074033367-Screenshot 2023-06-18 at 12.19.29.png").build()))
                                .title("Benefits of reading: Smart, Diligent, Happy").text(lorem).build(),
                        News.builder().author(users.get(4)).date(new Date(123454)).image(fileRepository.save(
                                        FileImg.builder().name("news 1").path("/Users/user/IdeaProjects/library/files/img/1687074109711-Screenshot 2023-06-18 at 12.19.39.png").build()))
                                .title("What books you should read in 2022?").text(lorem).build(),
                        News.builder().author(users.get(4)).date(new Date(123254)).image(fileRepository.save(
                                        FileImg.builder().name("news 1").path("/Users/user/IdeaProjects/library/files/img/1687073309921-Screenshot 2023-06-18 at 12.19.21.png").build()))
                                .title("10 Things you must know to improve your reading skills").text(lorem).build()
                )
        );
    }

    private void users() {
        Role userRole = Role
                .builder()
                .description("User")
                .name("USER")
                .permissions(Set.of())
                .build();

        userRole = roleRepository.save(userRole);

        users = userRepository.saveAll(
                List.of(
                        User
                                .builder()
                                .enabled(true)
                                .lastname("user 1")
                                .firstname("lll")
                                .bio("Book lovers")
                                .role(userRole)
                                .email("a_1@gmail.com")
                                .password(passwordEncoder.encode(superAdminPassword))
                                .avatar(fileRepository.save(
                                        FileImg
                                        .builder()
                                        .name("user 1 avatar")
                                        .path("/Users/user/IdeaProjects/library/files/img/staticMan.png")
                                        .build()))
                                .build(),
                        User
                                .builder()
                                .enabled(true)
                                .lastname("user 2")
                                .firstname("lll")
                                .bio("Book worm")
                                .role(userRole)
                                .email("a_2@gmail.com")
                                .password(passwordEncoder.encode(superAdminPassword))
                                .avatar(fileRepository.save(FileImg
                                        .builder()
                                        .name("user 2 avatar")
                                        .path("/Users/user/IdeaProjects/library/files/img/staticWoman.png")
                                        .build()))
                                .build(),
                        User
                                .builder()
                                .enabled(true)
                                .lastname("user 3")
                                .firstname("lll")
                                .bio("Book lovers")
                                .role(userRole)
                                .email("a_3@gmail.com")
                                .password(passwordEncoder.encode(superAdminPassword))
                                .avatar(fileRepository.save(FileImg
                                        .builder()
                                        .name("user 3 avatar")
                                        .path("/Users/user/IdeaProjects/library/files/img/userImage.png")
                                        .build()))
                                .build(),
                        User
                                .builder()
                                .enabled(true)
                                .lastname("user 4")
                                .bio("Writer")
                                .firstname("lll")
                                .role(userRole)
                                .email("a_4@gmail.com")
                                .password(passwordEncoder.encode(superAdminPassword))
                                .avatar(fileRepository.save(FileImg
                                        .builder()
                                        .name("user 4 avatar")
                                        .path("/Users/user/IdeaProjects/library/files/img/Screenshot 2023-11-25 at 23.35.19.png")
                                        .build()))
                                .build(),
                        User
                                .builder()
                                .enabled(true)
                                .lastname("user 5")
                                .firstname("lll")
                                .bio("Engineer")
                                .role(userRole)
                                .email("a_5@gmail.com")
                                .password(passwordEncoder.encode(superAdminPassword))
                                .avatar(fileRepository.save(FileImg
                                        .builder()
                                        .name("user 5 avatar")
                                        .path("/Users/user/IdeaProjects/library/files/img/1686658274561-640px-JudyBlume2009(cropped).jpg")
                                        .build()))
                                .build()
                )
        );
    }

    private void feedback() {
        feedbackRepository.saveAll(
                List.of(
                        Feedback.builder().date(new Date(5325342)).point(3).user(users.get(0))
                                .message("Shoping book in Bookoe is very easy. Quick delivery and fast respon. They services is awesome!").build(),
                        Feedback.builder().date(new Date(523442)).point(2).user(users.get(1))
                                .message("Quick delivery and fast respon. Shoping book in Bookoe is very easy.  They services is awesome!").build(),
                        Feedback.builder().date(new Date(123454)).point(5).user(users.get(2))
                                .message("They services is awesome! Shoping book in Bookoe is very easy. Quick delivery and fast respon.").build(),
                        Feedback.builder().date(new Date(521432)).point(4).user(users.get(3))
                                .message("Awesome! Quick delivery and fast respon. They services is Shoping book in Bookoe is very easy.").build(),
                        Feedback.builder().date(new Date(234344)).point(5).user(users.get(4))
                                .message("Very easy Quick delivery and fast respon. Shoping book in Bookoe is  They services is awesome!").build()
                )
        );
    }

    private void featured() {
        featuredRepository.saveAll(
                List.of(
                        Featured.builder().orderNum(12345L).book(books.get(0)).build(),
                        Featured.builder().orderNum(12645L).book(books.get(1)).build(),
                        Featured.builder().orderNum(12245L).book(books.get(2)).build(),
                        Featured.builder().orderNum(12145L).book(books.get(3)).build(),
                        Featured.builder().orderNum(125345L).book(books.get(4)).build(),
                        Featured.builder().orderNum(122345L).book(books.get(5)).build()
                )
        );
    }

    private void offers() {
        offerRepository.saveAll(
                List.of(
                        Offer
                                .builder()
                                .backgroundImg(
                                        fileRepository.save(
                                                FileImg.builder().name("offer 1").path("/Users/user/IdeaProjects/library/files/img/1686657815358-Screenshot 2023-06-13 at 16.23.32.png").build()
                                        )
                                )
                                .book(books.get(1))
                                .orderNum(1234L)
                                .build(),
                        Offer
                                .builder()
                                .backgroundImg(
                                        fileRepository.save(
                                                FileImg.builder().name("offer 2").path("/Users/user/IdeaProjects/library/files/img/1686658753907-Screenshot 2023-06-13 at 16.23.51.png").build()
                                        )
                                )
                                .book(books.get(3))
                                .orderNum(1534L)
                                .build(),
                        Offer
                                .builder()
                                .backgroundImg(
                                        fileRepository.save(
                                                FileImg.builder().name("offer 3").path("/Users/user/IdeaProjects/library/files/img/1686657851906-Screenshot 2023-06-13 at 16.23.41.png").build()
                                        )
                                )
                                .book(books.get(2))
                                .orderNum(1634L)
                                .build(),
                        Offer
                                .builder()
                                .backgroundImg(
                                        fileRepository.save(
                                                FileImg.builder().name("offer 4").path("/Users/user/IdeaProjects/library/files/img/1686657815358-Screenshot 2023-06-13 at 16.23.32.png").build()
                                        )
                                )
                                .book(books.get(5))
                                .orderNum(1884L)
                                .build()
                )
        );
    }

    private final String[] titles = {"Pushing Clouds", "SECONDS [Part 1]", "Terrible Madness", "REWORK", "Battle Drive", "All Good News", "The Misadventure of David", "A Heavy Lift", "Take Out Tango"};
    private final String[] languages = {"English", "English", "Chinese", "English", "German", "English", "English", "English", "Italian"};
    private final String[] isbn = {"121341381648 (ISBN13: 121341381648)", "121341381648 (ISBN13: 121341381648)", "121341381648 (ISBN13: 121341381648)", "121341381648 (ISBN13: 121341381648)", "121341381648 (ISBN13: 121341381648)", "121341381648 (ISBN13: 121341381648)", "121341381648 (ISBN13: 121341381648)", "121341381648 (ISBN13: 121341381648)", "121341381648 (ISBN13: 121341381648)"};
    private final Float[] prices = {12.3F, 10F, 11.5F, 12F, 5F, 87F, 19.9F, 23F, 17.56F};
    private final String lorem = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";
    private final String[] descriptions = {lorem + lorem + lorem, lorem + lorem + lorem, lorem + lorem + lorem, lorem + lorem + lorem, lorem + lorem + lorem, lorem + lorem + lorem, lorem + lorem + lorem, lorem + lorem + lorem, lorem + lorem + lorem};
    private final String[] publishers = {"Printarea Studios", "Printarea Studios", "Printarea Studios", "Printarea Studios", "Printarea Studios", "Printarea Studios", "Printarea Studios", "Printarea Studios", "Printarea Studios",};
    private final String[] editionFormat = {"Paperback, 450 Pages", "Paperback, 153 Pages", "Paperback, 250 Pages", "Paperback, 412 Pages", "Paperback, 98 Pages", "Paperback, 200 Pages", "Paperback, 430 Pages", "Paperback, 142 Pages", "Paperback, 184 Pages"};
    private final Date[] publishDate = {new Date(123456), new Date(9876543), new Date(23456789), new Date(123456), new Date(876543), new Date(123456), new Date(765443234), new Date(123456), new Date(654567),};

    private final AuthorRepository authorRepository;
    private final OfferRepository offerRepository;
    private final FeaturedRepository featuredRepository;
    private final FeedbackRepository feedbackRepository;
    private final NewsRepository newsRepository;
    private final ReviewRepository reviewRepository;


    private void bookGenre(List<Genre> genres, List<Book> books) {
        bookGenreRepository.saveAll(
                List.of(
                        BookGenre.builder().genre(genres.get(0)).book(books.get(0)).build(),
                        BookGenre.builder().genre(genres.get(11)).book(books.get(0)).build(),
                        BookGenre.builder().genre(genres.get(5)).book(books.get(0)).build(),
                        BookGenre.builder().genre(genres.get(10)).book(books.get(0)).build(),
                        BookGenre.builder().genre(genres.get(2)).book(books.get(1)).build(),
                        BookGenre.builder().genre(genres.get(0)).book(books.get(7)).build(),
                        BookGenre.builder().genre(genres.get(8)).book(books.get(7)).build(),
                        BookGenre.builder().genre(genres.get(4)).book(books.get(1)).build(),
                        BookGenre.builder().genre(genres.get(9)).book(books.get(6)).build(),
                        BookGenre.builder().genre(genres.get(3)).book(books.get(6)).build(),
                        BookGenre.builder().genre(genres.get(0)).book(books.get(5)).build(),
                        BookGenre.builder().genre(genres.get(7)).book(books.get(8)).build(),
                        BookGenre.builder().genre(genres.get(11)).book(books.get(8)).build(),
                        BookGenre.builder().genre(genres.get(0)).book(books.get(8)).build(),
                        BookGenre.builder().genre(genres.get(3)).book(books.get(2)).build(),
                        BookGenre.builder().genre(genres.get(5)).book(books.get(2)).build(),
                        BookGenre.builder().genre(genres.get(7)).book(books.get(3)).build(),
                        BookGenre.builder().genre(genres.get(9)).book(books.get(3)).build(),
                        BookGenre.builder().genre(genres.get(6)).book(books.get(3)).build(),
                        BookGenre.builder().genre(genres.get(10)).book(books.get(3)).build(),
                        BookGenre.builder().genre(genres.get(9)).book(books.get(4)).build(),
                        BookGenre.builder().genre(genres.get(4)).book(books.get(4)).build()
                )
        );
    }

    private void books(List<Book> books) {
        FileImg[] docs = {
                fileRepository.save(FileImg.builder().name("Pushing Clouds").path("/Users/user/IdeaProjects/library/files/pdf/1686658677803-64651018cef03.pdf").build()),
                fileRepository.save(FileImg.builder().name("SECONDS [Part 1]").path("/Users/user/IdeaProjects/library/files/pdf/1686658677803-64651018cef03.pdf").build()),
                fileRepository.save(FileImg.builder().name("Terrible Madness").path("/Users/user/IdeaProjects/library/files/pdf/1686658677803-64651018cef03.pdf").build()),
                fileRepository.save(FileImg.builder().name("REWORK").path("/Users/user/IdeaProjects/library/files/pdf/1686658677803-64651018cef03.pdf").build()),
                fileRepository.save(FileImg.builder().name("Battle Drive").path("/Users/user/IdeaProjects/library/files/pdf/1686658677803-64651018cef03.pdf").build()),
                fileRepository.save(FileImg.builder().name("All Good News").path("/Users/user/IdeaProjects/library/files/pdf/1686658677803-64651018cef03.pdf").build()),
                fileRepository.save(FileImg.builder().name("The Misadventure of David").path("/Users/user/IdeaProjects/library/files/pdf/1686658677803-64651018cef03.pdf").build()),
                fileRepository.save(FileImg.builder().name("A Heavy Lift").path("/Users/user/IdeaProjects/library/files/pdf/1686658677803-64651018cef03.pdf").build()),
                fileRepository.save(FileImg.builder().name("Take Out Tango").path("/Users/user/IdeaProjects/library/files/pdf/1686658677803-64651018cef03.pdf").build())
        };
               FileImg[] img = {
            fileRepository.save(FileImg.builder().name("Pushing Clouds").path("/Users/user/IdeaProjects/library/files/img/1686591489074-Screenshot 2023-06-12 at 19.51.46.png").build()),
            fileRepository.save(FileImg.builder().name("SECONDS [Part 1]").path("/Users/user/IdeaProjects/library/files/img/1686592490217-Screenshot 2023-06-12 at 22.46.55.png").build()),
            fileRepository.save(FileImg.builder().name("Terrible Madness").path("/Users/user/IdeaProjects/library/files/img/1686592252887-Screenshot 2023-06-12 at 22.46.33.png").build()),
            fileRepository.save(FileImg.builder().name("REWORK").path("/Users/user/IdeaProjects/library/files/img/1686658677828-Screenshot 2023-06-13 at 17.04.58.png").build()),
            fileRepository.save(FileImg.builder().name("Battle Drive").path("/Users/user/IdeaProjects/library/files/img/1686591603397-Screenshot 2023-06-12 at 20.01.46.png").build()),
            fileRepository.save(FileImg.builder().name("All Good News").path("/Users/user/IdeaProjects/library/files/img/Screenshot 2023-11-25 at 23.19.33.png").build()),
            fileRepository.save(FileImg.builder().name("The Misadventure of David").path("/Users/user/IdeaProjects/library/files/img/1686592386713-Screenshot 2023-06-12 at 22.46.44.png").build()),
            fileRepository.save(FileImg.builder().name("A Heavy Lift").path("/Users/user/IdeaProjects/library/files/img/1686592115737-Screenshot 2023-06-12 at 22.46.21.png").build()),
            fileRepository.save(FileImg.builder().name("Take Out Tango").path("/Users/user/IdeaProjects/library/files/img/Screenshot 2023-11-25 at 23.20.57.png").build())
    };

      List<Author> author = authorRepository.saveAll(List.of(
             Author.builder().avatar(
                            fileRepository.save(FileImg.builder().name("author 1").path("/Users/user/IdeaProjects/library/files/img/staticMan.png").build()))
                    .firstname("Kevin").lastname("Smiley").build(),
             Author.builder().avatar(
                             fileRepository.save(FileImg.builder().name("author 2").path("/Users/user/IdeaProjects/library/files/img/Screenshot 2023-11-25 at 23.35.19.png").build()))
                     .firstname("David").lastname("Here").build(),
             Author.builder().avatar(
                             fileRepository.save(FileImg.builder().name("author 3").path("/Users/user/IdeaProjects/library/files/img/1686658274561-640px-JudyBlume2009(cropped).jpg").build()))
                     .firstname("Margaretha").lastname("Helew").build(),
             Author.builder().avatar(
                             fileRepository.save(FileImg.builder().name("author 4").path("/Users/user/IdeaProjects/library/files/img/Screenshot 2023-11-25 at 23.34.53.png").build()))
                     .firstname("James").lastname("Wong").build(),
             Author.builder().avatar(
                             fileRepository.save(FileImg.builder().name("author 5").path("/Users/user/IdeaProjects/library/files/img/staticWoman.png").build()))
                     .firstname("Lidya").lastname("Humble").build(),
             Author.builder().avatar(
                             fileRepository.save(FileImg.builder().name("author 6").path("/Users/user/IdeaProjects/library/files/img/userImage.png").build()))
                     .firstname("Franklin").lastname("Junior").build()
      ));

    final Author[] authors = {author.get(0), author.get(1), author.get(2), author.get(0), author.get(5), author.get(4), author.get(3), author.get(1), author.get(5)};

        for (int i = 0; i < 9; i++) {
            books.add(Book
                    .builder()
                    .title(titles[i])
                    .language(languages[i])
                    .isbn(isbn[i])
                    .price(prices[i])
                    .description(descriptions[i])
                    .publisher(publishers[i])
                    .editionFormat(editionFormat[i])
                    .publishedDate(publishDate[i])
                    .document(docs[i])
                    .image(img[i])
                    .author(authors[i])
                    .discount(discounts.get(i))
                    .build());
        }
        books = bookRepository.saveAll(books);
    }

    private void recommendationBooks(List<Book> books) {
        Recommendation recommendation = recommendationRepository
                .save(
                        Recommendation
                                .builder()
                                .backgroundImage(
                                        fileRepository.save(
                                                FileImg
                                                        .builder()
                                                        .name("recommendation 1")
                                                        .path("/Users/user/IdeaProjects/library/files/img/1686640980568-recommend.jpg")
                                                        .build()
                                        )
                                )
                                .title("Recommended For You")
                                .subtitle("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                                .build()
                );

        recommendationBookRepository.saveAll(
                List.of(
                        RecommendationBook
                                .builder()
                                .recommendation(recommendation)
                                .book(books.get(0))
                                .build(),
                        RecommendationBook
                                .builder()
                                .recommendation(recommendation)
                                .book(books.get(1))
                                .build(),
                        RecommendationBook
                                .builder()
                                .recommendation(recommendation)
                                .book(books.get(2))
                                .build(),
                        RecommendationBook
                                .builder()
                                .recommendation(recommendation)
                                .book(books.get(3))
                                .build(),
                        RecommendationBook
                                .builder()
                                .recommendation(recommendation)
                                .book(books.get(4))
                                .build(),
                        RecommendationBook
                                .builder()
                                .recommendation(recommendation)
                                .book(books.get(5))
                                .build(),
                        RecommendationBook
                                .builder()
                                .recommendation(recommendation)
                                .book(books.get(6))
                                .build()
                )
        );

        recommendation = recommendationRepository
                .save(
                        Recommendation
                                .builder()
                                .backgroundImage(
                                        fileRepository.save(
                                                FileImg
                                                        .builder()
                                                        .name("recommendation 2")
                                                        .path("/Users/user/IdeaProjects/library/files/img/1686641012360-popular.jpg")
                                                        .build()
                                        )
                                )
                                .title("Popular in 2022")
                                .subtitle("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                                .build()
                );

        recommendationBookRepository.saveAll(
                List.of(
                        RecommendationBook
                                .builder()
                                .recommendation(recommendation)
                                .book(books.get(8))
                                .build(),
                        RecommendationBook
                                .builder()
                                .recommendation(recommendation)
                                .book(books.get(7))
                                .build(),
                        RecommendationBook
                                .builder()
                                .recommendation(recommendation)
                                .book(books.get(6))
                                .build(),
                        RecommendationBook
                                .builder()
                                .recommendation(recommendation)
                                .book(books.get(5))
                                .build(),
                        RecommendationBook
                                .builder()
                                .recommendation(recommendation)
                                .book(books.get(4))
                                .build(),
                        RecommendationBook
                                .builder()
                                .recommendation(recommendation)
                                .book(books.get(3))
                                .build(),
                        RecommendationBook
                                .builder()
                                .recommendation(recommendation)
                                .book(books.get(2))
                                .build()
                )
        );
    }

    private void promotion() {
        promotionRepository.saveAll(
                List.of(
                        Promotion
                                .builder()
                                .backgroundImage(
                                        fileRepository.save(
                                                FileImg
                                                        .builder()
                                                        .name("promotion 1")
                                                        .path("/Users/user/IdeaProjects/library/files/img/1686593054828-promo-bg.jpg")
                                                        .build()
                                        )
                                )
//                                .btn1("Get the deal")
//                                .btn2("See other promos")
                                .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris")
                                .header("BACK TO SCHOOL")
                                .subtitle("for our student community")
                                .title("Special 50% Off")
                                .build(),
                        Promotion
                                .builder()
                                .backgroundImage(
                                        fileRepository.save(
                                                FileImg
                                                        .builder()
                                                        .name("promotion 2")
                                                        .path("/Users/user/IdeaProjects/library/files/img/1686593054828-promo-bg.jpg")
                                                        .build()
                                        )
                                )
//                                .btn1("Get the deal")
//                                .btn2("See other promos")
                                .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris")
                                .header("BACK TO SCHOOL")
                                .subtitle("for our student community")
                                .title("Special 50% Off")
                                .build()
                )
        );
    }

    private void genres() {
        genres = List.of(
                Genre
                        .builder()
                        .name("Action")
                        .build(),
                Genre
                        .builder()
                        .name("Adventure")
                        .build(),
                Genre
                        .builder()
                        .name("Comedy")
                        .build(),
                Genre
                        .builder()
                        .name("Crime")
                        .build(),
                Genre
                        .builder()
                        .name("Drama")
                        .build(),
                Genre
                        .builder()
                        .name("Fantasy")
                        .build(),
                Genre
                        .builder()
                        .name("Horror")
                        .build(),
                Genre
                        .builder()
                        .name("Law")
                        .build(),
                Genre
                        .builder()
                        .name("Mystery")
                        .build(),
                Genre
                        .builder()
                        .name("Professional")
                        .build(),
                Genre
                        .builder()
                        .name("Romance")
                        .build(),
                Genre
                        .builder()
                        .name("TV Series")
                        .build()
        );

        genres = genreRepository.saveAll(genres);
    }

}
