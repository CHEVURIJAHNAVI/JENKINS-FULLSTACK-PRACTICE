import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './style.css';
import config from './config.js';

const ReportManager = () => {
  const [reports, setReports] = useState([]);
  const [report, setReport] = useState({
    id: '',
    citizenName: '',
    contact: '',
    location: '',
    condition: '',
    temperature: '',
    date: '',
    notes: ''
  });
  const [idToFetch, setIdToFetch] = useState('');
  const [fetchedReport, setFetchedReport] = useState(null);
  const [message, setMessage] = useState('');
  const [editMode, setEditMode] = useState(false);

  const baseUrl = `${config.url}/reportapi`;

  useEffect(() => {
    fetchAllReports();
  }, []);

  const fetchAllReports = async () => {
    try {
      const res = await axios.get(`${baseUrl}/all`);
      setReports(res.data);
    } catch (error) {
      setMessage('Failed to fetch reports.');
    }
  };

  const handleChange = (e) => {
    setReport({ ...report, [e.target.name]: e.target.value });
  };

  const validateForm = () => {
    const requiredFields = ['id', 'citizenName', 'contact', 'location', 'condition', 'date'];
    for (let field of requiredFields) {
      if (!report[field] || report[field].toString().trim() === '') {
        setMessage(`Please fill out the ${field} field.`);
        return false;
      }
    }
    return true;
  };

  const addReport = async () => {
    if (!validateForm()) return;
    try {
      await axios.post(`${baseUrl}/add`, report);
      setMessage('Report added successfully.');
      fetchAllReports();
      resetForm();
    } catch (error) {
      setMessage('Error adding report. Make sure the backend is running.');
    }
  };

  const updateReport = async () => {
    if (!validateForm()) return;
    try {
      await axios.put(`${baseUrl}/update`, report);
      setMessage('Report updated successfully.');
      fetchAllReports();
      resetForm();
    } catch (error) {
      setMessage('Error updating report.');
    }
  };

  const deleteReport = async (id) => {
    try {
      const res = await axios.delete(`${baseUrl}/delete/${id}`);
      setMessage(res.data);
      fetchAllReports();
    } catch (error) {
      setMessage('Error deleting report.');
    }
  };

  const getReportById = async () => {
    try {
      const res = await axios.get(`${baseUrl}/get/${idToFetch}`);
      setFetchedReport(res.data);
      setMessage('');
    } catch (error) {
      setFetchedReport(null);
      setMessage('Report not found.');
    }
  };

  const handleEdit = (r) => {
    setReport(r);
    setEditMode(true);
    setMessage(`Editing report with ID ${r.id}`);
  };

  const resetForm = () => {
    setReport({
      id: '',
      citizenName: '',
      contact: '',
      location: '',
      condition: '',
      temperature: '',
      date: '',
      notes: ''
    });
    setEditMode(false);
  };

  return (
    <div className="student-container">

      {message && (
        <div className={`message-banner ${message.toLowerCase().includes('error') ? 'error' : 'success'}`}>
          {message}
        </div>
      )}

      <h2>Report Management</h2>

      <div>
        <h3>{editMode ? 'Edit Report' : 'Add Report'}</h3>
        <div className="form-grid">
          <input type="number" name="id" placeholder="Report ID" value={report.id} onChange={handleChange} />
          <input type="text" name="citizenName" placeholder="Citizen Name" value={report.citizenName} onChange={handleChange} />
          <input type="text" name="contact" placeholder="Contact" value={report.contact} onChange={handleChange} />
          <input type="text" name="location" placeholder="Location" value={report.location} onChange={handleChange} />
          <select name="condition" value={report.condition} onChange={handleChange}>
            <option value="">Select Weather Condition</option>
            <option value="Sunny">Sunny</option>
            <option value="Rainy">Rainy</option>
            <option value="Cloudy">Cloudy</option>
            <option value="Stormy">Stormy</option>
            <option value="Snowy">Snowy</option>
          </select>
          <input type="text" name="temperature" placeholder="Temperature" value={report.temperature} onChange={handleChange} />
          <input type="date" name="date" placeholder="Date" value={report.date} onChange={handleChange} />
          <input type="text" name="notes" placeholder="Additional Notes" value={report.notes} onChange={handleChange} />
        </div>

        <div className="btn-group">
          {!editMode ? (
            <button className="btn-blue" onClick={addReport}>Add Report</button>
          ) : (
            <>
              <button className="btn-green" onClick={updateReport}>Update Report</button>
              <button className="btn-gray" onClick={resetForm}>Cancel</button>
            </>
          )}
        </div>
      </div>

      <div>
        <h3>Get Report By ID</h3>
        <input type="number" value={idToFetch} onChange={(e) => setIdToFetch(e.target.value)} placeholder="Enter ID" />
        <button className="btn-blue" onClick={getReportById}>Fetch</button>

        {fetchedReport && (
          <div>
            <h4>Report Found:</h4>
            <pre>{JSON.stringify(fetchedReport, null, 2)}</pre>
          </div>
        )}
      </div>

      <div>
        <h3>All Reports</h3>
        {reports.length === 0 ? (
          <p>No reports found.</p>
        ) : (
          <div className="table-wrapper">
            <table>
              <thead>
                <tr>
                  {Object.keys(report).map((key) => (
                    <th key={key}>{key}</th>
                  ))}
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {reports.map((r) => (
                  <tr key={r.id}>
                    {Object.keys(report).map((key) => (
                      <td key={key}>{r[key]}</td>
                    ))}
                    <td>
                      <div className="action-buttons">
                        <button className="btn-green" onClick={() => handleEdit(r)}>Edit</button>
                        <button className="btn-red" onClick={() => deleteReport(r.id)}>Delete</button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>

    </div>
  );
};

export default ReportManager;
