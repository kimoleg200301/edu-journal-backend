package org.example.edujournalbackend.group;

import org.example.edujournalbackend.subject.Subject;

import java.util.List;

public interface GroupDao {
    Group findByGroupId(Long edu_group_id);
    List<Group> findAllGroups();
    Boolean save(Group group);
    Boolean update(Group group);
    Boolean delete(Long edu_group_id);

    // добавить теперь нахождение не добавленных студентов в группы для возможности добавления
}
