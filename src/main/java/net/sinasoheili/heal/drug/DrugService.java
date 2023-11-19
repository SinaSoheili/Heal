package net.sinasoheili.heal.drug;

import lombok.RequiredArgsConstructor;
import net.sinasoheili.heal.user.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DrugService {

    private final DrugRepository drugRepository;
    private final UserService userService;

    @Transactional
    public DrugDto registerNewDrugInternal(DrugDto drugDto) {
        DrugEntity drugEntity = drugDtoToDrugEntity(drugDto);
        registerNewDrugInternal(drugEntity);
        return drugEntityToDrugDto(drugEntity);
    }

    @Transactional
    public void registerNewDrugInternal(DrugEntity drugEntity) {
        drugRepository.persist(drugEntity);
    }

    private DrugEntity drugDtoToDrugEntity(DrugDto drugDto) {
        DrugEntity drugEntity = new DrugEntity();
        BeanUtils.copyProperties(drugDto, drugEntity);
        drugEntity.setUser(userService.loadUserByIdInternal(drugDto.getUserId()));
        return drugEntity;
    }

    private DrugDto drugEntityToDrugDto(DrugEntity drugEntity) {
        DrugDto drugDto = new DrugDto();
        BeanUtils.copyProperties(drugEntity, drugDto);
        drugDto.setUserId(drugEntity.getUser().getId());
        return drugDto;
    }
}
