package com.example.packminigames.Controller.REST_Controllers.RecordController;

import com.example.packminigames.Controller.REST_Controllers.AbstractBasicRESTController;
import com.example.packminigames.Models.DTO.RecordDTO;
import com.example.packminigames.Service.DAO.RecordService.RecordService_impl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/record")
public class RecordREST_Controller_impl extends AbstractBasicRESTController<RecordService_impl, RecordDTO> implements IRecordREST_Controller
{
    public RecordREST_Controller_impl(RecordService_impl recordServiceImpl) {
        super(recordServiceImpl);
    }
}
