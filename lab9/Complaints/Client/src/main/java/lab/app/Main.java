package lab.app;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

public class Main {
    private static final String BASE_URL = "http://localhost:8080/Server-1.0-SNAPSHOT/api/complaints";

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        try {
            List<ComplaintDTO> allComplaints = client.target(BASE_URL)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<>() {});
            System.out.println("a) Wszystkie skargi:");
            allComplaints.forEach(System.out::println);

            ComplaintDTO openComplaint = allComplaints.stream()
                    .filter(c -> "open".equals(c.getStatus()))
                    .findFirst()
                    .orElse(null);

            if (openComplaint == null) {
                System.out.println("Brak skarg o statusie 'open'.");
                return;
            }

            ComplaintDTO fetchedOpenComplaint = client.target(BASE_URL + "/" + openComplaint.getId())
                    .request(MediaType.APPLICATION_JSON)
                    .get(ComplaintDTO.class);
            System.out.println("b) Jedna otwarta skarga:");
            System.out.println(fetchedOpenComplaint);

            fetchedOpenComplaint.setStatus("closed");
            client.target(BASE_URL + "/" + fetchedOpenComplaint.getId())
                    .request()
                    .put(Entity.entity(fetchedOpenComplaint, MediaType.APPLICATION_JSON));
            System.out.println("c) Zmieniono status skargi na 'closed'.");

            List<ComplaintDTO> openComplaints = client.target(BASE_URL)
                    .queryParam("status", "open")
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<>() {});
            System.out.println("d) Wszystkie otwarte skargi:");
            openComplaints.forEach(System.out::println);
        } finally {
            client.close();
        }
    }

    public static class ComplaintDTO {
        private Long id;
        private String complaintDate;
        private String complaintText;
        private String author;
        private String status;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getComplaintDate() {
            return complaintDate;
        }

        public void setComplaintDate(String complaintDate) {
            this.complaintDate = complaintDate;
        }

        public String getComplaintText() {
            return complaintText;
        }

        public void setComplaintText(String complaintText) {
            this.complaintText = complaintText;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "ComplaintDTO{" +
                    "id=" + id +
                    ", complaintDate='" + complaintDate + '\'' +
                    ", complaintText='" + complaintText + '\'' +
                    ", author='" + author + '\'' +
                    ", status='" + status + '\'' +
                    '}';
        }
    }
}
