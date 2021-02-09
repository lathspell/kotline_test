package de.lathspell.test.jpa

import de.lathspell.test.model.Group
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
interface GroupRepo : JpaRepository<Group, UUID>
