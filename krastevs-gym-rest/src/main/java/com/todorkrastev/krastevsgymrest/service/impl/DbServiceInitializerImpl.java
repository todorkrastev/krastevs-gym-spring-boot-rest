package com.todorkrastev.krastevsgymrest.service.impl;

import com.todorkrastev.krastevsgymrest.model.entity.Activity;
import com.todorkrastev.krastevsgymrest.repository.ActivityRepository;
import com.todorkrastev.krastevsgymrest.service.DbServiceInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbServiceInitializerImpl implements DbServiceInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(DbServiceInitializerImpl.class);

    private final ActivityRepository activityRepository;

    public DbServiceInitializerImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public void init() {
        LOGGER.info("------------------------Database startup begins------------------------");

        if (activityRepository.count() == 0) {
            activityRepository.saveAll(initActivities());
        }

        LOGGER.info("-------------------------Database startup ends-------------------------");
    }

    private List<Activity> initActivities() {
        return List.of(
                new Activity()
                        .setTitle("Free Weights")
                        .setDescription("If you suffer from acute iron deficiency, you've come to the right place. In the Free Weights Area, you can let off steam on the gym80 Olympia Premium barbells or dumbbells up to 150 kg.")
                        .setImageURL("https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648980/krastevs-gym/imgs/home/weights_otkv2w.jpg"),
                new Activity()
                        .setTitle("Machines")
                        .setDescription("Indestructible, incomparable and incredibly effective: With the ultimate equipment from gym80. The King of Machines you train all muscles absolutely precisely thanks to the excellent biomechanics.")
                        .setImageURL("https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648981/krastevs-gym/imgs/home/machines_r8v6au.jpg"),
                new Activity()
                        .setTitle("Cardio Area")
                        .setDescription("Take your endurance to the next level with top equipment from Precor and Matrix and look forward to a gigantic selection - in Constance even between 10 meter high trees!")
                        .setImageURL("https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648980/krastevs-gym/imgs/home/cardio_e8p7uu.jpg"),
                new Activity()
                        .setTitle("Boxing")
                        .setDescription("You won't meet Wladimir Klitschko or Alexander Povetkin in our gym, but the fun in our boxing classes is guaranteed.")
                        .setImageURL("https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648980/krastevs-gym/imgs/home/boxing_enbvlj.jpg"),
                new Activity()
                        .setTitle("PT & Coaching")
                        .setDescription("You can't get ahead and always have an excuse? Then it's high time for the best personal training ever! Experience individual 1:1 support, pure motivation, and maximum effectiveness.")
                        .setImageURL("https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648980/krastevs-gym/imgs/home/coaching_wixxkv.jpg"),
                new Activity()
                        .setTitle("Yoga")
                        .setDescription("If you are looking for peace of mind and solitude, come and join our yoga classes.")
                        .setImageURL("https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648980/krastevs-gym/imgs/home/yoga_j7dugv.jpg"),
                new Activity()
                        .setTitle("VR Training & More")
                        .setDescription("At Krastev's Gym, we offer you two strong fitness class formats: VR Workouts and Live Classes. All classes are free and suitable for all levels.")
                        .setImageURL("https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648980/krastevs-gym/imgs/home/vr_t8ozjs.jpg"),
                new Activity()
                        .setTitle("Posing Room")
                        .setDescription("First pump, then pose. In the fully mirrored room in Constance, that's no problem. Because we have not spared the anabolic light here, you can put your body optimally illuminated in the scene.")
                        .setImageURL("https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648984/krastevs-gym/imgs/home/posing_eowtqn.jpg"),
                new Activity()
                        .setTitle("Sauna & Recovery")
                        .setDescription("If you work hard, you can also relax. Ideally in our separate men's and women's sauna. Time to relax.")
                        .setImageURL("https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648985/krastevs-gym/imgs/home/sauna_hldgsa.jpg"),
                new Activity()
                        .setTitle("Swimming Pool")
                        .setDescription("Krastev's Gym Fullerton features an outdoor pool and indoor hot tub, open year-round. Swim your way to better health and stay cool in the Constance sun with our unlimited membership.")
                        .setImageURL("https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648983/krastevs-gym/imgs/home/pool_fzwsxw.jpg"),
                new Activity()
                        .setTitle("Krastev's Gym Nutrition")
                        .setDescription("When you train, you get your body in top shape. But you also demand a lot from it. Krastev's Gym Nutrition ensures that your body is supplied with everything it needs before, during and after your workout.")
                        .setImageURL("https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648983/krastevs-gym/imgs/home/nutrition_jzewte.jpg"),
                new Activity()
                        .setTitle("Krastev's Gym Shop")
                        .setDescription("At Krastev's Gym you have a wide selection of products specially selected for you by us. Useful fitness accessories, and the perfect clothes for your workout. You can pick up your order in your Krastev's Gym, or have it conveniently delivered to your home.")
                        .setImageURL("https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648986/krastevs-gym/imgs/home/shop_l5ilbv.jpg")
        );
    }
}
