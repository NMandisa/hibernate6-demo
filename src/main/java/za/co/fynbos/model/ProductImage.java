package za.co.fynbos.model;

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product_image",schema = "db_commerce")
public class ProductImage implements Serializable {
    @Id
    @SequenceGenerator(name = "product_image_generator", sequenceName = "sequence_product_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_image_generator")
    @Column(name = "product_image_id")
    private Long productImageId;

    @Column(name = "product_image_url")
    private String productImageUrl;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_has_product_images",
            joinColumns = @JoinColumn(name = "product_image_id", referencedColumnName = "product_image_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id",foreignKey=@ForeignKey(name = "product_id_fk")
            ))
    private Product product;

    public  ProductImage(String productImageUrl){
        this.productImageUrl=productImageUrl;
    }
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(productImageId).toHashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ProductImage other)) return false;
        return new EqualsBuilder().append(productImageId, other.productImageId).isEquals();
    }
    public String toString(){
        return "product image Id : "+productImageId+" product image url: "+ productImageUrl +"\n product : "+product.toString()+"\n";
    }
}
