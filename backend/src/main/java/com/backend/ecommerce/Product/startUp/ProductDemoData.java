package com.backend.ecommerce.Product.startUp;

import com.backend.ecommerce.Product.dataAccess.mongo.BulletPointsRepositoryMongo;
import com.backend.ecommerce.Product.dataAccess.mongo.ProductRepositoryMongo;
import com.backend.ecommerce.Product.entities.entity.Image;
import com.backend.ecommerce.Product.entities.entity.PriceType;
import com.backend.ecommerce.Product.entities.entity.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductDemoData {

    @Autowired
    private ProductRepositoryMongo productRepositoryMongo;

    @EventListener(ApplicationReadyEvent.class)
    public void createDemoData() {
        List<String> bulletPoints = new ArrayList<>();
        bulletPoints.add("25 in 1 MULTIFUNCTIONAL VEGETABLE CUTTER - You can easily chop, dice, slice or grate your veggie with our vegetable chopper and slicer. This vegetable chopper is a practical kitchen tool, our mandoline slicer offers multiple functions in one highly versatile kitchen gadget. The onion chopper is made of top quality materials such as high quality stainless steel blades and food grade materials. The vegetable chopper can cut most types of vegetables and fruits.");
        bulletPoints.add("EASY CLEAN, 8 INTERCHANGEABLE SHARP BLADES - You can easily change the blades and clean the blades with our special aparat. Vegetable Chopper Slicer contains 8 different sizes of fruit chopper blades interchangeable blades for thick/thin slicing, dicing or chunking, shredding, grinding and chopping. Onion Chopper rust-resistant heavy-duty 420 stainless steel maintains sharp edges for crisp, smooth cuts and grating.");
        bulletPoints.add("INNOVATIVE DESIGN - The built-in cutting lid allows you to cut food directly into the large 1.2L food container, eliminating the need for a knife and cutting board. This prevents food from sliding off while slicing.");
        bulletPoints.add("IMPROVES YOUR HEALTH AND SAVES TIME - Our mandoline slicer has 1 glove for prevent your fingers until cutting. So you can easily protect yourself thanks to our glove. Chopping, mincing and slicing ingredients for desired dishes can take a lot of time and effort, like tomatoes, cabbage, onions, garlic, etc. The new upgraded vegetable chopper reduces the time for the whole process, so you won't have trouble cooking or chopping vegetables and fruits.");
        bulletPoints.add("GIFTS FOR ALL OCCASIONS - We have designed exquisite package for our vegetable chopper, it is perfect for gifts. It will be an awesome gift for Business, house warming for new home.");

        List<String> comments = new ArrayList<>();
        comments.add("I am very happy with my purchase. It really made things so much easier. It comes with a bunch of grater parts in different size and shapes. It is such a time saver. Highly recommended \uD83D\uDE4C\uD83C\uDFFB\uD83D\uDE4C\uD83C\uDFFB\uD83D\uDE4C\uD83C\uDFFB\uD83D\uDC4C");
        comments.add("From my wife: This is not the first vegetable chopper that I've owned. I like this vegetable chopper much more than my previous one because it comes with a lot of attachments, and I can actually get rid of some of my kitchen tools.");
        comments.add("This set a great space saver since you don't have to store a bunch of separate bulky choppers. Only real downfall is there's no way to sharpen the blades when they become dull. Worth the money. Would make a good housewarming gift.");


        Image image_1 = new Image(Image.ImageType.ONE, "https://m.media-amazon.com/images/I/810P97qy2cL._AC_SL1500_.jpg");
        Image image_2 = new Image(Image.ImageType.TWO, "https://m.media-amazon.com/images/I/81iEWYaaGzL._AC_SL1500_.jpg");
        Image image_3 = new Image(Image.ImageType.THEREE, "https://m.media-amazon.com/images/I/71ixQn0+etL._AC_SL1500_.jpg");
        Image image_4 = new Image(Image.ImageType.FOUR, "https://m.media-amazon.com/images/I/71aPpyA84YL._AC_SL1500_.jpg");
        Image image_5 = new Image(Image.ImageType.FIVE, "https://m.media-amazon.com/images/I/71b7mIU+5xL._AC_SL1500_.jpg");

        List<Image> images = new ArrayList<>();
        images.add(image_1);
        images.add(image_2);
        images.add(image_3);
        images.add(image_4);
        images.add(image_5);

        Product product = new Product(
                "ID-B0CH8H1TT8",
                "25 in 1 Vegetable Chopper Mandoline Slicer for Kitchen Veggie Slicer with Container Cheese Grater Onion Slicer Potato Slicer, Carrot Peeler, Egg Separator Multifunctional Vegetable Cutter by Mandeline",
                "MANDE",
                "Kitchen & Dining",
                "B0CH8H1TT8",
                10,
                true,
                4.8,
                20.99,
                PriceType.USD,
                "2",
                "3",
                "1"
        );
        productRepositoryMongo.save(product);
    }

}
