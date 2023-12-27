package org.doit.ik.mapper;

import java.util.List;

import org.doit.ik.domain.BookDTO;

public interface BookMapper {

   List<BookDTO> list();

   BookDTO get(String seq);

   void add(BookDTO dto);

}