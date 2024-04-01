package com.simplenotes.view.main;

import com.simplenotes.model.service.security.SecurityService;
import com.simplenotes.view.login.LoginView;
import com.simplenotes.view.note.NoteView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Theme(variant = Lumo.DARK)
public class MainLayout extends AppLayout implements AppShellConfigurator {
    private final Tabs menu;
    private H1 viewTitle;
    private final SecurityService securityService;

    public MainLayout(SecurityService securityService) {
        this.securityService = securityService;

        setPrimarySection(Section.DRAWER);


        addToNavbar(true, headerContent());

        menu = createMenu();
        addToDrawer(createDrawerContent(menu));
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){

        }
        addToDrawer(logoutButton());

    }

    private Component createDrawerContent(Tabs menu) {
        VerticalLayout layout = new VerticalLayout();


        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.getThemeList().set("spacing-s", true);
        layout.setAlignItems(Alignment.STRETCH);


        HorizontalLayout logoLayout = new HorizontalLayout();
        logoLayout.setId("logo");
        logoLayout.setAlignItems(Alignment.CENTER);
        logoLayout.add(new H1("Simple Notes"));

        layout.add(logoLayout, menu);
        return layout;
    }

    private Component headerContent() {

        HorizontalLayout layout = new HorizontalLayout();
        layout.setId("header");
        layout.getThemeList().set("dark", true);
        layout.setWidthFull();
        layout.setSpacing(false);
        layout.setAlignItems(Alignment.CENTER);

        layout.add(new DrawerToggle());
        viewTitle = new H1();
        layout.add(viewTitle);

        return layout;
    }

    private Tabs createMenu() {
        final Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.addThemeVariants(TabsVariant.LUMO_MINIMAL);
        tabs.setId("tabs");
        tabs.add(menuTabs());
        return tabs;
    }

    private Tab[] menuTabs() {
        return new Tab[] {
                createTab("Main page", MainView.class),
                createTab("My Notes", NoteView.class),
        };

    }

    private static Tab createTab(String text,
                                 Class<? extends Component> navigationTarget) {
        final Tab tab = new Tab();
        tab.add(new RouterLink(text, navigationTarget));
        ComponentUtil.setData(tab, Class.class, navigationTarget);
        return tab;
    }

    private Optional<Tab> getTabForComponent(Component component) {
        return menu.getChildren()
                .filter(tab -> ComponentUtil.getData(tab, Class.class)
                        .equals(component.getClass()))
                .findFirst().map(Tab.class::cast);
    }

    private String getCurrentPageTitle() {
        return getContent().getClass().getAnnotation(PageTitle.class).value();
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        getTabForComponent(getContent()).ifPresent(menu::setSelectedTab);
        viewTitle.setText(getCurrentPageTitle());
    }

    private Button logoutButton() {
        if (isAnonymousUser()) {
            return new Button("Logout", click ->
                    securityService.logout());
        }
        return new Button("Login", c ->
                getUI().ifPresent(ui -> ui.navigate("login")));
    }

    private static boolean isAnonymousUser() {
        return !SecurityContextHolder.getContext().getAuthentication().getName().isBlank() &&
                !SecurityContextHolder.getContext().getAuthentication().getName().isEmpty() &&
                !SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser");
    }
}
