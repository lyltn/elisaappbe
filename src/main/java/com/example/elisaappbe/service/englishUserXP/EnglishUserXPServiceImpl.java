package com.example.elisaappbe.service.englishUserXP;

import com.example.elisaappbe.dto.req.EnglishUserXPRequest;
import com.example.elisaappbe.dto.resp.EnglishRankingUserResponse;
import com.example.elisaappbe.dto.resp.EnglishUserXPResponse;
import com.example.elisaappbe.model.EnglishAchievement;
import com.example.elisaappbe.model.EnglishUserXP;
import com.example.elisaappbe.model.User;
import com.example.elisaappbe.repository.EnglishAchievementRepository;
import com.example.elisaappbe.repository.EnglishUserXPRepository;
import com.example.elisaappbe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
                    userXP.getCurrentAchievement().getDescription(),
                    userXP.getCurrentAchievement().getIconUrl(),
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

    @Override
    public List<EnglishRankingUserResponse> getRankingUserXP() {
        List<EnglishUserXP> getListUserXP = userXPRepository.findAllWithDetails();

        List<EnglishRankingUserResponse> responseList = new ArrayList<>();

        for (EnglishUserXP xp : getListUserXP) {
            EnglishRankingUserResponse dto = new EnglishRankingUserResponse();
            // Map User ID
            if (xp.getUser() != null) {
                dto.setUserId(xp.getUser().getUserId());
                dto.setFullName(xp.getUser().getFullName());
                dto.setAvatarImage(xp.getUser().getAvatarImage());
            }
            // Map Achievement (Kiểm tra null để tránh lỗi)
            EnglishAchievement ach = xp.getCurrentAchievement();
            if (ach != null) {
                dto.setAchievementsID(ach.getAchievementId()); // Hãy thay bằng getter ID thực tế của bạn
                dto.setTitle(ach.getTitle());
                dto.setDescription(ach.getDescription());
                dto.setIcon_url(ach.getIconUrl());
            } else {
                // Xử lý giá trị mặc định nếu không có danh hiệu
                dto.setAchievementsID(0);
                dto.setTitle("Chưa có danh hiệu");
            }
            // Map Total XP
            dto.setTotalXP(xp.getTotalXP());
            // Thêm vào list
            responseList.add(dto);
        }
        return responseList;
    }
}
