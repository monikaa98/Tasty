package project.tastyfood.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import project.tastyfood.model.entity.Picture;
import project.tastyfood.model.service.PictureServiceModel;
import project.tastyfood.repository.PictureRepository;
import project.tastyfood.service.PictureService;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@Transactional
public class PictureServiceImpl implements PictureService {
    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;
    public static final String UPLOAD_DIR="pictures";

    public PictureServiceImpl(PictureRepository pictureRepository, ModelMapper modelMapper) {
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PictureServiceModel addPicture(PictureServiceModel pictureServiceModel) {
        Picture picture =this.modelMapper.map(pictureServiceModel,Picture.class);
        if(pictureServiceModel.getFile()!=null){
            String name=pictureServiceModel.getFile().getOriginalFilename();
            long size=pictureServiceModel.getFile().getSize();
            try{
                File currentDir=new File(UPLOAD_DIR);
                if(!currentDir.exists()){
                    currentDir.mkdirs();
                }
                String path=currentDir.getAbsolutePath()+"/"+pictureServiceModel.getFile().getOriginalFilename();
                path=new File(path).getAbsolutePath();
                File f=new File(path);
                FileCopyUtils.copy(pictureServiceModel.getFile().getInputStream(),new FileOutputStream(f));

            } catch (IOException e) {
                e.printStackTrace();
            }
            String path1="/"+UPLOAD_DIR+"/"+pictureServiceModel.getFile().getOriginalFilename();
            picture.setPictureUrl(path1);
        }

        PictureServiceModel pictureServiceModel1=new PictureServiceModel();
        picture=this.pictureRepository.saveAndFlush(picture);
        pictureServiceModel1.setPicture(picture);
        return pictureServiceModel1;
    }

    @Override
    public PictureServiceModel getPicture(String pictureUrl) {
        Picture picture=this.pictureRepository.findByPictureUrl(pictureUrl);
        PictureServiceModel pictureServiceModel=new PictureServiceModel();
        pictureServiceModel.setPicture(picture);
        return pictureServiceModel;
    }
}
