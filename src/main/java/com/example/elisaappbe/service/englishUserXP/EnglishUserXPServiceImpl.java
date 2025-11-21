package com.example.elisaappbe.service.englishUserXP;

import com.example.elisaappbe.dto.req.EnglishUserXPRequest;
import com.example.elisaappbe.dto.resp.EnglishUserXPResponse;
import com.example.elisaappbe.model.EnglishUserXP;
import com.example.elisaappbe.model.User;
import com.example.elisaappbe.repository.EnglishAchievementRepository;
import com.example.elisaappbe.repository.EnglishUserXPRepository;
import com.example.elisaappbe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnglishUserXPServiceImpl implements EnglishUserXPService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EnglishAchievementRepository achievementRepository;

    @Autowired
    private EnglishUserXPRepository userXPRepository;

    @Override
    public EnglishUserXPResponse getUserXP(long userId) {
        Optional<EnglishUserXP> getUserXP = userXPRepository.findByUser_UserId(userId);
        if(getUserXP.isPresent()){
            EnglishUserXP userXP = getUserXP.get();
            return new EnglishUserXPResponse(
                    userXP.getUser().getUserId(),
                    userXP.getCurrentAchievement().getAchievementId(),
                    userXP.getCurrentAchievement().getTitle(),
                    userXP.getCurrentAchievement().getIconUrl(),
                    userXP.getCurrentAchievement().getDescription(),
                    userXP.getTotalXP()
            );
        }
        return null;
    }

    @Override
    public EnglishUserXPResponse updateUserXP(EnglishUserXPRequest res) {
        Optional<EnglishUserXP> getUserXP = userXPRepository.findByUser_UserId(res.getUserId());
        if(getUserXP.isPresent()){
            EnglishUserXP userXP = getUserXP.get();
            userXP.setTotalXP(res.getTotalXP());
            userXP.setCurrentAchievement(achievementRepository.findById(res.getAchievementsID()).get());
            EnglishUserXP savedUserXP = userXPRepository.save(userXP);
            return new EnglishUserXPResponse(
                    savedUserXP.getUser().getUserId(),
                    savedUserXP.getCurrentAchievement().getAchievementId(),
                    savedUserXP.getCurrentAchievement().getTitle(),
                    savedUserXP.getCurrentAchievement().getIconUrl(),
                    savedUserXP.getCurrentAchievement().getDescription(),
                    savedUserXP.getTotalXP()
            );
        }
        return null;
    }

    @Override
    public EnglishUserXPResponse createUserXP(long userId) {
        EnglishUserXP newUserXP = new EnglishUserXP();
        Optional<User> getUser =  userRepository.findById(userId);
        if(getUser.isPresent()){
            newUserXP.setUser(getUser.get());
            newUserXP.setTotalXP(0L);
            newUserXP.setCurrentAchievement(achievementRepository.findById(1L).get());
            EnglishUserXP savedUserXP = userXPRepository.save(newUserXP);
            return new EnglishUserXPResponse(
                    savedUserXP.getUser().getUserId(),
                    savedUserXP.getCurrentAchievement().getAchievementId(),
                    savedUserXP.getCurrentAchievement().getTitle(),
                    savedUserXP.getCurrentAchievement().getIconUrl(),
                    savedUserXP.getCurrentAchievement().getDescription(),
                    savedUserXP.getTotalXP()
            );
        }
        return null ;
    }
}
