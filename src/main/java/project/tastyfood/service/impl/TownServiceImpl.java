package project.tastyfood.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.tastyfood.error.TownNotFoundException;
import project.tastyfood.model.entity.Town;
import project.tastyfood.model.service.PictureServiceModel;
import project.tastyfood.model.service.TownServiceModel;
import project.tastyfood.model.view.TownViewModel;
import project.tastyfood.repository.TownRepository;
import project.tastyfood.service.PictureService;
import project.tastyfood.service.TownService;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final PictureService pictureService;

@Autowired
    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper, PictureService pictureService) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.pictureService = pictureService;
    }

    @Override
    public TownServiceModel findById(String id) {
        TownServiceModel townServiceModel = new TownServiceModel();
        Town town=this.townRepository.findById(id).orElse(null);
        if(town==null){
            throw new TownNotFoundException(id);
        }else {
            townServiceModel = this.modelMapper.map(town, TownServiceModel.class);
            townServiceModel.setPictureUrl(town.getPicture().getPictureUrl());
            townServiceModel.setId(town.getId());
        }

        return townServiceModel;


    }

    @Override
    public void addTown(TownServiceModel townServiceModel, PictureServiceModel pictureServiceModel) {
        PictureServiceModel pictureServiceModel1 = this.pictureService.addPicture(pictureServiceModel);
        Town town = this.modelMapper.map(townServiceModel, Town.class);
        town.setPicture(pictureServiceModel1.getPicture());
        Town townCheck=this.townRepository.findByName(townServiceModel.getName()).orElse(null);
        if(townCheck!=null){
            throw new EntityExistsException(String.format("Town with %s as name, already exists",
                    townServiceModel.getName()));
        }
        this.townRepository.saveAndFlush(town);

    }

    @Override
    public List<TownViewModel> getAllTowns() {
        List<Town>towns=this.townRepository.findAll();
        List<TownViewModel>townsViewModels=new ArrayList<>();
        for (Town town : towns) {
            TownViewModel townViewModel=new TownViewModel();
            townViewModel.setName(town.getName());
            townViewModel.setId(town.getId());
            townViewModel.setPictureUrl(town.getPicture().getPictureUrl());
            townsViewModels.add(townViewModel);
        }

        return townsViewModels;
    }
}
