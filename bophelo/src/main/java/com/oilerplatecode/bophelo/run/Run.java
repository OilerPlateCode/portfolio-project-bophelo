package com.oilerplatecode.bophelo.run;

import java.time.LocalDateTime;

public record Run(Integer id,
                  String title,
                  LocalDateTime startedOn,
                  LocalDateTime completedOn,
                  Integer meters,
                  Location location) {

}
