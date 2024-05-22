package dev.bc.sas.web;

import java.util.List;

import dev.bc.sas.validation.PlayerIdList;
import dev.bc.sas.validation.TeamId;

record RosterModel(@TeamId(nullable = true) Long teamId, @PlayerIdList List<Long> playerIds) {

}
