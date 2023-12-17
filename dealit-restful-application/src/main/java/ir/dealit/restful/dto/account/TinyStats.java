package ir.dealit.restful.dto.account;

import ir.dealit.restful.dto.notification.Notification;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class TinyStats {
    private String message;
    private Double balance;
    private Page<Notification> notifications;
    private int newMessage;
    private int paymentConfirmation;
    private @Nullable FreelancerTinyStats freelancer;
    private @Nullable ClientTinyStats client;

    @Data
    @Builder
    public static class FreelancerTinyStats {
        private int activeProposals;
        private int activeJobs;
        private int newInvitations;
        private int totalConnection;
        private double income;
    }

    @Data
    @Builder
    public static class ClientTinyStats {
        private int newProposals;
        private int activeAds;
        private double outcome;
    }
}
