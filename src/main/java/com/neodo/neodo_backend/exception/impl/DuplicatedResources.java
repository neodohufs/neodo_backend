package com.neodo.neodo_backend.exception.impl;

import com.neodo.neodo_backend.common.response.Response;
import com.neodo.neodo_backend.exception.CustomException;

public class DuplicatedResources extends CustomException {

  public DuplicatedResources(Response response) {
    super(response);
  }
}
