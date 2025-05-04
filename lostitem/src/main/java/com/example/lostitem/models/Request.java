package com.example.lostitem.models;
import com.example.lostitem.models.RequestStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;
@Entity
@Table(name = "requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requestId;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    private LocalDateTime reviewedAt;

    @Column(columnDefinition = "TEXT")
    private String thankYouMessage;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user; // 작성자

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Users admin; // 관리자

    @ManyToOne
    @JoinColumn(name = "requester_id")
    private Users requester; // 요청자
}
