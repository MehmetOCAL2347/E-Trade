package com.backend.ecommerce.Product.business.manager;

import com.backend.ecommerce.Product.business.service.ImageListService;
import com.backend.ecommerce.Product.dataAccess.mongo.ImageListRepositoryMongo;
import com.backend.ecommerce.Product.entities.entity.Image;
import com.backend.ecommerce.Product.entities.entity.ImageList;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageListListManager implements ImageListService {

    @Autowired
    private ImageListRepositoryMongo imageListRepositoryMongo;

    @Override
    public String saveImage(List<Image> imageList, String productId) {

        // Fotograflar önce aws yada minio gibi bir yere yüklenmeli sonrasında url şeklinde kaydedilmeli
        ImageList images = new ImageList();
        images.setImages(imageList);
        images.setProductId(productId);

        ImageList savedImageList = imageListRepositoryMongo.save(images);
        return savedImageList.getId();
    }
}
