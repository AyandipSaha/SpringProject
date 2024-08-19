package org.dipayan.SpringStarter.config;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.dipayan.SpringStarter.models.Account;
import org.dipayan.SpringStarter.models.Authority;
import org.dipayan.SpringStarter.models.Post;
import org.dipayan.SpringStarter.services.AccountService;
import org.dipayan.SpringStarter.services.AuthorityService;
import org.dipayan.SpringStarter.services.PostService;
import org.dipayan.SpringStarter.util.constants.Privillages;
import org.dipayan.SpringStarter.util.constants.Roles;

@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityService authorityService;

    @Override
    public void run(String... args) throws Exception {
        
        for (Privillages auth : Privillages.values()) {
            Authority authority = new Authority();
            authority.setId(auth.getId());
            authority.setName(auth.getPrivillage());
            authorityService.save(authority);
        }

        Account account01 = new Account();
        Account account02 = new Account();
        Account account03 = new Account();
        Account account04 = new Account();
        Account account05 = new Account();
        Account account06 = new Account();

        account01.setEmail("ayandipsaha61@gmail.com");
        account01.setPassword("password");
        account01.setFirstname("Ayandip");
        account01.setLastname("Saha");
        account01.setGender("Male");
        account01.setAge(22);
        account01.setDate_of_birth(LocalDate.parse("2002-10-05"));
        account01.setRole(Roles.ADMIN.getRole());

        account02.setEmail("biriyani.saha@gmail.com");
        account02.setPassword("password");
        account02.setFirstname("Riki");
        account02.setLastname("Multiverse");
        account02.setGender("Male");
        account02.setAge(20);
        account02.setDate_of_birth(LocalDate.parse("2004-07-10"));

        account03.setEmail("Ashish@org.in");
        account03.setPassword("password");
        account03.setFirstname("Ashish");
        account03.setLastname("Shekh");
        account03.setGender("Male");
        account03.setAge(20);
        account03.setDate_of_birth(LocalDate.parse("2004-07-10"));
        account03.setRole(Roles.EDITOR.getRole());

        account04.setEmail("biplabdey898@gmail.com"); //Super_Editor
        account04.setPassword("password");
        account04.setFirstname("Bikram");
        account04.setLastname("Dey");
        account04.setGender("Male");
        account04.setAge(22);
        account04.setDate_of_birth(LocalDate.parse("2002-01-25"));
        account04.setRole(Roles.EDITOR.getRole());

        account05.setEmail("midoriya@edu.com");
        account05.setPassword("password");
        account05.setFirstname("Izuku");
        account05.setLastname("Midoriya");
        account05.setGender("Male");
        account05.setAge(19);
        account05.setDate_of_birth(LocalDate.parse("2005-12-20"));

        account06.setEmail("ochako@edu.in");
        account06.setPassword("password");
        account06.setFirstname("Uraraka");
        account06.setLastname("Ochako");
        account06.setGender("Female");
        account06.setAge(18);
        account06.setDate_of_birth(LocalDate.parse("2006-08-03"));

        Set<Authority> authorities = new HashSet<>();
        authorityService.findById(Privillages.RESET_ANY_USER_PASSWORD.getId()).ifPresent(authorities::add);
        authorityService.findById(Privillages.ACCESS_ADMIN_PANEL.getId()).ifPresent(authorities::add);

        account04.setAuthorities(authorities);

        accountService.save(account01);
        accountService.save(account02);
        accountService.save(account03);
        accountService.save(account04);
        accountService.save(account05);
        accountService.save(account06);

        List<Post> posts = postService.getAll();
        if (posts.size() == 0) {
            
            Post post01 = new Post();
            post01.setTitle("Spring Boot Model-view-controller Framework");
            post01.setBody("""
                
                <h3><strong>Model-view-controller Framework</strong></h3>
                
<p><a href="https://upload.wikimedia.org/wikipedia/commons/thumb/7/7b/Spring5JuergenHoeller2.jpg/330px-Spring5JuergenHoeller2.jpg"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/7/7b/Spring5JuergenHoeller2.jpg/330px-Spring5JuergenHoeller2.jpg"></a></p>
                
<p><i>The Spring Framework features its own <a href="https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller">model-view-controller</a> (MVC) <a href="https://en.wikipedia.org/wiki/Web_application_framework">web application framework</a>, which was not originally planned. The Spring developers decided to write their own Web framework as a reaction to what they perceived as the poor design of the (then) popular <a href="https://en.wikipedia.org/wiki/Jakarta_Struts">Jakarta Struts</a> Web framework, as well as deficiencies in other available frameworks. In particular, they felt there was insufficient separation between the presentation and request handling layers, and between the request handling layer and the model.</i></p>
                
<p><i>Like Struts, Spring MVC is a request-based framework. The framework defines <a href="https://en.wikipedia.org/wiki/Strategy_pattern">strategy</a> interfaces for all of the responsibilities that must be handled by a modern request-based framework. The goal of each interface is to be simple and clear so that it's easy for Spring MVC users to write their own implementations, if they so choose. MVC paves the way for cleaner front end code. All interfaces are tightly coupled to the <a href="https://en.wikipedia.org/wiki/Java_Servlet">Servlet API</a>. This tight coupling to the Servlet API is seen by some as a failure on the part of the Spring developers to offer a high level of abstraction for Web-based applications. However, this coupling ensures that the features of the Servlet API remain available to developers while offering a high abstraction framework to ease working with it.</i></p>

<p><i>The <code>DispatcherServlet</code> class is the <a href="https://en.wikipedia.org/wiki/Front_controller">front controller</a> of the framework and is responsible for delegating control to the various interfaces during the execution phases of an <a href="https://en.wikipedia.org/wiki/Hypertext_Transfer_Protocol">HTTP request</a>.</i></p>

<p><i>The most important interfaces defined by Spring MVC, and their responsibilities, are listed below:</i></p>

<i><ul>
    <li><code>Controller:</code> comes between Model and View to manage incoming requests and redirect to proper response. Controller will map the http request to corresponding methods. It acts as a gate that directs the incoming information. It switches between going into Model or View.</li>
    <li><code>HandlerAdapter:</code> responsible for execution of objects that handle incoming requests.</li>
    <li><code>HandlerInterceptor:</code> responsible for intercepting incoming requests. Comparable, but not equal to Servlet filters (use is optional and not controlled by DispatcherServlet).</li>
    <li><code>HandlerMapping:</code> responsible for selecting objects that handle incoming requests (handlers) based on any attribute or condition internal or external to those requests</li>
    <li><code>LocaleResolver:</code> responsible for resolving and optionally saving of the locale of an individual user.</li>
    <li><code>MultipartResolver:</code> facilitate working with file uploads by wrapping incoming requests.</li>
    <li><code>View:</code> responsible for returning a response to the client. The View should not contain any business logic and should only present the data encapsulated by the Model. Some requests may go straight to View without going to the Model part; others may go through all three.</li>
    <li><code>ViewResolver:</code> responsible for selecting a View based on a logical name for the View (use is not strictly required).</li>
    <li><code>Model:</code> responsible for encapsulating business data. The Model is exposed to the view by the controller. (use is not strictly required).</li>
</ul></i>

<p><i>Each strategy interface above has an important responsibility in the overall framework. The abstractions offered by these interfaces are powerful, so to allow for a set of variations in their implementations. Spring MVC ships with implementations of all these interfaces and offers a feature set on top of the Servlet API. However, developers and vendors are free to write other implementations. Spring MVC uses the Java <code><a href="https://docs.oracle.com/en/java/javase/19/docs/api/java.base/java/util/Map.html">java.util.Map</a></code> interface as a data-oriented abstraction for the <code>Model</code> where keys are expected to be <code><a href="https://docs.oracle.com/en/java/javase/19/docs/api/java.base/java/lang/String.html">String</a></code> values.</i></p>

<p><i>The ease of testing the implementations of these interfaces is one important advantage of the high level of abstraction offered by Spring MVC. DispatcherServlet is tightly coupled to the Spring inversion of control container for configuring the web layers of applications. However, web applications can use other parts of the Spring Framework, including the container, and choose not to use Spring MVC.</i></p>

            """);
            post01.setAccount(account01);
            postService.save(post01);

            Post post02 = new Post();
            post02.setTitle("GitHub");
            post02.setBody("""
                <p><i>Git is a <a href="https://en.wikipedia.org/wiki/Distributed_version_control">distributed version control</a> <a href="https://en.wikipedia.org/wiki/Software_system">system</a> that tracks versions of files. It is often used to control source code by programmers collaboratively <a href="https://en.wikipedia.org/wiki/Software_development">developing</a> software.</i></p>

<p><i>Design goals of Git include speed, <a href="https://en.wikipedia.org/wiki/Data_integrity">data integrity</a>, and support for distributed, non-linear workflows thousands of parallel branches running on different computers.</i></p>

<p><i>Git was created for use in the development of the Linux kernel by Linus Torvalds and others developing the kernel.</i></p>

<p><i>As with most other distributed version control systems, and unlike most client-server systems, Git maintains a local copy 
of the entire repository, a.k.a. repo, with history and version-tracking abilities, independent of network access or a central server. A repo is stored on each computer in a standard directory with additional, hidden files to provide version control capabilities. Git provides features to synchronize changes between repos that share history; copied (cloned) from each other. For collaboration, Git supports synchronizing with repos on remote machines. Although all repos (with the same history) are peers, developers often use a central server to host a repo to hold an integrated copy.</i></p>

<p><i>Git is a free and open-source software shared under the <a href="https://en.wikipedia.org/wiki/GNU_General_Public_License">GPL-2.0-only license</a>.</i></p>
            """);
            post02.setAccount(account04);
            postService.save(post02);

            Post post03 = new Post();
            post03.setTitle("Multiverse of Gaming Timeline");
            post03.setBody("""
                <h4>Gaming Youtube Channel</h4>

<p><i>Hello everyone this is my youtube channel, please do Subscribe to my channel. I am uploading very exciting content of 
GTA V game and many other games of PC and I think all videos will be interesting to you guys.</p>
<p>Here is the link - 
<a href="https://www.youtube.com/@multiverseofgamingtimeline">Multiverse of Gaming Timeline.</a></i></p>

<p><i>Here is also an interesting video.....</i></p>

<iframe width="560" height="315" src="https://www.youtube-nocookie.com/embed/4_H64UkdADo?si=25_KgCOIvGn0BJEG" 
title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; 
picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
            """);
            post03.setAccount(account02);
            postService.save(post03);

            Post post04 = new Post();
            post04.setTitle("TCS");
            post04.setBody("""
                <p><i><b>Tata Consultancy Services (TCS)</b> is an Indian multinational information technology (IT) services and consulting company headquartered in Mumbai. 
It is a part of the Tata Group and operates in 150 locations across 46 countries. In March 2024, TCS reported that it had 601,546 employees worldwide. 
TCS is the second-largest Indian company by market capitalization, the most valuable IT service brands worldwide, and the top Big Tech (India) company.
In 2012, it was the world's second-largest user of U.S. H-1B visas.</i></p>

<p><i>As of 2021, it was ranked seventh on the Fortune India 500 list. In September 2021, TCS recorded a market capitalization of US$200 billion, making it the first Indian 
IT tech company to do so. In 2016-2017, parent company Tata Sons owned 72.05% of TCS and more than 70% of Tata Sons' dividends were generated by TCS.</i></p>
            """);
            post04.setAccount(account03);
            postService.save(post04);

            Post post05 = new Post();
            post05.setTitle("One For All Quirk");
            post05.setBody("""
                <p><i>One For All was a transferable Quirk that could be passed on from one user to the next. Its name came from its nature: 
being "one" Quirk for "all" people. For the Quirk to be transferred, the recipient must ingest a sample of the predecessor's DNA (ex: a strand of hair or a drop of blood).</i></p>

<p><i>The origin of One For All is itself a powerful will that refused to submit to All For One. For this reason, One For All cannot be stolen by any means. 
The Quirk can only be transferred to another person if the current wielder intends to pass it on, as demonstrated when Stain ingested some of Izuku's blood but did not inherit the Quirk. 
However, according to All Might, while One For All cannot be stolen, it can be forcibly transferred, as the recipient need not agree to inherit the Quirk in the first place. 
Since One For All can't be forcibly taken, it is one of the only Quirks that can be used in combat against All For One, as any user of All For One would be able to steal most other Quirks.</i></p>
            """);
            post05.setAccount(account05);
            postService.save(post05);

            Post post06 = new Post();
            post06.setTitle("Floating Quirk");
            post06.setBody("""
                <p><i><b>Zero Gravity (Zero Gurabiti?)</b>: Ochaco's Quirk gives her the power to nullify the effects of gravity on solid targets (living and nonliving) 
by touching them with the pads on her fingertips, causing them to become weightless and float. She is able to cancel the effect of her Quirk by touching her finger pads together.</i></p>

<p><i>Her Quirk is good for restraining, as those affected will be less capable of fighting back since they will keep floating upwards until they are released. Ochaco takes advantage of 
ravaged areas to gather wreckage while also exploiting the consequences of a destructive Quirk. She can easily clean up disaster zones while simultaneously figuring out the timing 
of her movements so as not to damage others.</i></p>

<p><i>The main drawback of Ochaco's Quirk is that if the upper weight limit (around three tons) is exceeded, or if Ochaco floats herself, she will suffer from severe nausea. 
As part of her training, Ochaco considerably reduced resultant nausea and increased her weight limit, as well as developed her ability to float herself for short periods of time without becoming nauseous.</i></p>

<p><i>During the Final War in her battle against Himiko, Ochaco's Quirk undergoes an Awakening, allowing the effects of her Zero Gravity to spread among different targets touching each other, without Ochaco 
having to touch them all herself. This Awakening also removed Ochaco's weight limit of three tons and allowed her to set down her weightless targets in a slower fashion, and without pressing her fingertips together.</i></p>
            """);
            post06.setAccount(account06);
            postService.save(post06);

        }
    }
    
}
