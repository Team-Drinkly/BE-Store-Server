package com.drinkhere.drinklystore.application.presentation;

import com.drinkhere.drinklystore.application.presentation.docs.StoreAdminConrollerDocs;
import com.drinkhere.drinklystore.application.presentation.docs.StoreControllerDocs;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/store/m")
public class StoreController  implements StoreControllerDocs {
}
