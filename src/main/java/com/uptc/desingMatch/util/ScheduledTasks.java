package com.uptc.desingMatch.util;

/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.uptc.desingMatch.models.Desing;
import com.uptc.desingMatch.service.DesingService;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static final SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
    
    
    
	@Autowired 
	private ServletContext context;
	
	@Autowired
	private DesingService desingService;
	
    @Scheduled(fixedRate = 10000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        log.info("Comprobando diseños");
        List<Desing> list = desingService.getListDesingNotAvailable();
        log.info("Total diseños a convertir {}", list.size());
        for (Desing desing : list) {
			try {
				String pathImage = context.getRealPath(Const.PATH_IMG_DISENOS)+ "/" +desing.getNameCompany()+"/"+desing.getIdDraft()+"/"+desing.getUrlImgOriginal();
				String copyPath = context.getRealPath(Const.PATH_FINAL_DISENOS)+ "/" +desing.getNameCompany()+"/"+desing.getIdDraft()+"/"+"finalDesing-"+desing.getUrlImgOriginal();
				System.out.println("Url : " + pathImage);
			
				ImageManager.processImage(pathImage, copyPath, (desing.getNameDesigner() + "  " 
			+ desing.getLastNameDesigner()), dateFormat2.format(new Date()));
				 ManagerCorreos.sendMail(Const.MAIL_USER, Const.PASSWORD_USER, 
	                        desing.getEmail(), Const.MAIL_SUBJECT, Const.MAIL_MESSAGE);
				desing.setUrlImgConvert("finalDesing-"+desing.getUrlImgOriginal());
				desing.setState("D");
				desingService.save(desing);
			} catch (Exception e) {
				
			}
		}
        
    }
}
