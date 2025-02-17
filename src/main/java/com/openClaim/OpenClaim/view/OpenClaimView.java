package com.openClaim.OpenClaim.view;

import com.openClaim.OpenClaim.model.Address;
import com.openClaim.OpenClaim.model.Claim;
import com.openClaim.OpenClaim.model.ClaimStatus;
import com.openClaim.OpenClaim.service.ClaimService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

import java.io.InputStream;
import java.time.LocalDate;

@Route("/openclaim")
@PermitAll
public class OpenClaimView extends VerticalLayout {

    private final ClaimService claimService;
    private final VerticalLayout contentLayout;

    public OpenClaimView(ClaimService claimService) {
        this.claimService = claimService;

        addClassName("openclaim-view");

        contentLayout = new VerticalLayout();
        contentLayout.add(createClaimForm()); // Mostra il form del sinistro all'inizio


     Tab claimTab = new Tab(VaadinIcon.CAR.create(),new Span("Dettagli del sinistro"));
     Tab documentsTab = new Tab(VaadinIcon.RECORDS.create(),new Span("Documenti"));
     claimTab.setEnabled(true);
        // Set the icon on top
        for (Tab tab : new Tab[] { claimTab, documentsTab }) {
            tab.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
        }
        Tabs tabs = new Tabs(claimTab, documentsTab);
        tabs.addClassName("tabs");

        tabs.addSelectedChangeListener(e -> {
            contentLayout.removeAll();
            if (e.getSelectedTab().equals(claimTab)) {
                // claimTab
                contentLayout.add(createClaimForm());
            }else{
                // documentsTab
                contentLayout.add(createDocumentUploadForm());
            }
        });

        tabs.setOrientation(Tabs.Orientation.HORIZONTAL);


        Button goBackToHome = new Button("Torna alla home");
        goBackToHome.addClassName("back-to-home-button");
        goBackToHome.addClickListener(e -> {
            UI.getCurrent().navigate("/openclaim/home");
        });
        add(tabs, contentLayout,goBackToHome);

    }

    /**
     * Crea il form per inserire i dettagli del sinistro
     */
    private FormLayout createClaimForm() {

        LocalDate date = LocalDate.now();
        DatePicker datePicker = new DatePicker("Data accadimento");
        datePicker.setRequired(true);
        datePicker.setMax(date.plusMonths(9));
        datePicker.setHelperText("La Data di accadimento deve essere compresa negli ultimi 9 mesi");
        datePicker.addClassName("date-picker");

        IntegerField numberOfCarsInvolved = new IntegerField("Numero di automobili coinvolte");
        numberOfCarsInvolved.setMin(1);
        numberOfCarsInvolved.setMax(4);
        numberOfCarsInvolved.setClearButtonVisible(true);
        numberOfCarsInvolved.setStepButtonsVisible(true);
        numberOfCarsInvolved.addClassName("number-of-cars-involved");

        TextField street = new TextField("Via/Piazza");
        street.setHelperText("Inserisci il nome della via o della piazza");
        street.addClassName("street");
        TextField city = new TextField("Città");
        city.addClassName("city");
        TextField postalCode = new TextField("CAP");
        postalCode.addClassName("postal-code");
        postalCode.setPattern("[0-9]{5}");
        postalCode.setMaxLength(5);
        TextField province = new TextField("Provincia");
        province.setPattern("[A-Z]{2}");
        province.setMaxLength(2);
        province.addClassName("province");
        TextField description = new TextField("Breve descrizione dell'incidente");
        description.addClassName("description");
        description.setMaxLength(100);
        description.setWidthFull();

        Button save = new Button("Salva", e -> {
            Claim claim = new Claim();
            claim.setClaimDate(datePicker.getValue());
            claim.setClaimStatus(ClaimStatus.OPEN);
            Address address = new Address();
            address.setStreet(street.getValue());
            address.setCity(city.getValue());
            address.setPostalCode(postalCode.getValue());
            address.setProvince(province.getValue());
            claim.setAddress(address);
            claimService.saveClaim(claim);
            UI.getCurrent().navigate("/openclaim/home");
        });
        save.addClassName("save-button");

        FormLayout form = new FormLayout();
        form.add(datePicker, numberOfCarsInvolved, street, city, postalCode, province, description, save);
        return form;
    }


    /**
     * Crea il form per il caricamento dei documenti
     */
    private VerticalLayout createDocumentUploadForm() {
        MemoryBuffer buffer = new MemoryBuffer();
        Upload upload = new Upload(buffer);
        upload.setAcceptedFileTypes(".jpg", ".png", ".pdf"); // Permette solo immagini e PDF
        upload.setMaxFiles(3); // Max 3 file caricabili
        upload.setDropLabel(new Span("Trascina i file qui o clicca per caricare"));
        upload.addClassName("document-upload");

        upload.addSucceededListener(event -> {
            InputStream fileStream = buffer.getInputStream();
            String fileName = event.getFileName();
            long fileSize = event.getContentLength();

            // Logica per salvare il file (es. database o storage esterno)
            System.out.println("Caricato file: " + fileName + " (" + fileSize + " bytes)");
        });

        return new VerticalLayout(upload);
    }
}
