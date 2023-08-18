package turboaz.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Search_TEXT")
public class SearchTextChangeEntity {
    @Id
    @Column(name = "seach_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mail")
    private String mail;

    @Column(name = "url", length = 1000)
    private String url;

    @Column(name = "old_description", length = 10000)
    private String descriptionText;

    @Column(name = "new_description", length = 10000)
    private String newDescription;
}
