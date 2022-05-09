package com.Iasoftware.nomina.infrastructure.gateways;

import com.Iasoftware.nomina.core.domain.Report;
import com.Iasoftware.nomina.core.gateways.ReportRepository;
import com.Iasoftware.nomina.infrastructure.gateways.models.ReportDBO;
import com.Iasoftware.nomina.shared.domain.Week;
import org.springframework.stereotype.Repository;


import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SqlReportRepository implements ReportRepository {
    private final DataSource dataSource;

    public SqlReportRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void create(Report report) {
        String sql = "INSERT INTO report (id,id_technical,id_service,start_datetime,end_datetime) VALUES (?,?,?,?,?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, report.getId().getValue());
            preparedStatement.setString(2, report.getIdTechnical().getValue());
            preparedStatement.setString(3, report.getIdService().getValue());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(report.getStartDate()));
            preparedStatement.setTimestamp(5, Timestamp.valueOf(report.getEndDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("Error querying database", exception);
        }

    }


    @Override
    public List<Report> query(Week week) {
        String sql = "SELECT * FROM report WHERE id_technical = ? AND start_datetime >= ?   ";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, week.getIdTechnical().getValue());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(week.getStartWeek()));
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Report> result = new ArrayList<>();
            ReportDBO(resultSet,result);
            resultSet.close();
            return result;
        } catch (SQLException exception) {
            throw new RuntimeException("Error querying database", exception);
        }
    }


    public void ReportDBO(ResultSet resultSet, List<Report> result) throws SQLException {
        while (resultSet.next()) {
            ReportDBO dbo = new ReportDBO();
            dbo.setId(resultSet.getString("id"));
            dbo.setIdTechnical(resultSet.getString("id_technical"));
            dbo.setIdService(resultSet.getString("id_service"));
            dbo.setStartDate(resultSet.getTimestamp("start_datetime").toLocalDateTime());
            dbo.setEndDate(resultSet.getTimestamp("end_datetime").toLocalDateTime());
            Report domainReport = dbo.toDomain();
            result.add(domainReport);
        }
    }
}