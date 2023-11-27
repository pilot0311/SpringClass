package org.doit.ik.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IpData {

	private String ip;
    private String log;
    private Long bno;
}
