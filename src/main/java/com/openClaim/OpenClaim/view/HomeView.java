package com.openClaim.OpenClaim.view;

import com.openClaim.OpenClaim.service.SecurityService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.annotation.security.PermitAll;


@Route("openclaim/home")
//@RolesAllowed({"user","admin"})
@PermitAll
@PageTitle("HomePage")
public class HomeView extends AppLayout {

    private final SecurityService securityService;
    public HomeView(SecurityService securityService) {
        this.securityService = securityService;

        addClassName("home-layout");
        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1("Open Claim");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");

        SideNav nav = getSideNav();

        nav.addClassName("navbar");
        Scroller scroller = new Scroller(nav);
        scroller.setClassName(LumoUtility.Padding.SMALL);
        scroller.addClassName("scroller");

        String u = securityService.getAuthenticatedUser().getUsername();
        Button logout = new Button("Log out " + u, e -> securityService.logout());
        logout.addClassName("logout-button");

        addToDrawer(scroller);
        addToNavbar(toggle, title, logout);
    }

    private SideNav getSideNav() {
        SideNav sideNav = new SideNav();
        sideNav.addItem(
                new SideNavItem("Dashboard", "/dashboard",
                        VaadinIcon.DASHBOARD.create()),
                new SideNavItem("Open Claim", "/openclaim", VaadinIcon.CAR.create()),
                new SideNavItem("Customers", "/customers",
                        VaadinIcon.USER_HEART.create()),
                new SideNavItem("Tasks", "/tasks", VaadinIcon.LIST.create()),
                new SideNavItem("Analytics", "/analytics",
                        VaadinIcon.CHART.create()));
        return sideNav;
    }
}
