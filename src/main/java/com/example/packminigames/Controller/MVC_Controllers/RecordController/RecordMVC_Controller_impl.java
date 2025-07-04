package com.example.packminigames.Controller.MVC_Controllers.RecordController;

import com.example.packminigames.Controller.MVC_Controllers.AbstractBasicMVCController;
import com.example.packminigames.Models.DTO.RecordDTO;
import com.example.packminigames.Service.DAO.RecordService.RecordService_impl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/record")
public class RecordMVC_Controller_impl extends AbstractBasicMVCController<RecordService_impl, RecordDTO> implements IRecordMVC_Controller
{
    public RecordMVC_Controller_impl(RecordService_impl recordServiceImpl, String basePath, String dtoAttributeName, Class<RecordDTO> recordDTOClass) {
        super(recordServiceImpl, basePath, dtoAttributeName, recordDTOClass);
    }
}
