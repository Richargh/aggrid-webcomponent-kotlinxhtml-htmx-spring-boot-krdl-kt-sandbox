/** @type {GridApi} **/
let gridApi;
/** @type {GridOptions} **/
const gridOptions = {
    columnDefs: [
        {field: 'athlete', minWidth: 220},
        {field: 'country', minWidth: 200},
        {field: 'year'},
        {field: 'sport', minWidth: 200},
        {field: 'gold'},
        {field: 'silver'},
        {field: 'bronze'},
    ],

    defaultColDef: {
        flex: 1,
        minWidth: 100,
    },

    pagination: true,
    // allows the user to select the page size from a predefined list of page sizes
    paginationPageSizeSelector: [5, 10, 20],
    paginationPageSize: 5,
    cacheBlockSize: 10, // how many rows are requested from server

    rowModelType: 'serverSide',
};

/** @type {IServerSideDatasource} **/
const serverSideDataSource = {
    getRows: (params) => {
        console.log('[Datasource] - rows requested by grid: ', params.request);

        fetch('/data?' + new URLSearchParams({
            startRow: params.request.startRow,
            endRow: params.request.endRow,
        }))
            .then((response) => response.json())
            .then(function (data) {
                /** @type {LoadSuccessParams} **/
                const successData = {
                    rowData: data.rows,
                    rowCount: data.total
                };
                params.success(successData);
            })
            .catch((error) => {
                console.error(`Failed to fetch: ${error}`);
                params.fail();
            });
    }
}

customElements.define("my-ag-grid", class extends HTMLElement {

    connectedCallback() {
        const gridDiv = this.querySelector('#myGrid');
        gridApi = agGrid.createGrid(gridDiv, gridOptions);
        gridApi.setGridOption('serverSideDatasource', serverSideDataSource);
    }

});

/**
 * @typedef {Object} Grid
 *
 * @param {(HTMLElement) => GridApi} createGrid
 *
 * Original is the TypeScript file at {@link https://github.com/ag-grid/ag-grid/blob/latest/grid-community-modules/core/src/ts/grid.ts}
 */

/**
 * @typedef {Object} GridApi
 */

/**
 * @typedef {Object} GridOptions
 * @template TData
 * @property {'infinite' | 'viewport' | 'clientSide' | 'serverSide'} [rowModelType]
 *
 * Original is the TypeScript file at {@link https://github.com/ag-grid/ag-grid/blob/latest/grid-community-modules/core/src/ts/entities/gridOptions.ts}
 */

/**
 * @typedef {Object} ColumnVO
 * @property {string} id
 * @property {string} displayName
 * @property {string} [field]
 * @property {string} [aggFunc]
 *
 * Original is the TypeScript file at {@link https://github.com/ag-grid/ag-grid/blob/latest/grid-community-modules/core/src/ts/interfaces/iColumnVO.ts}
 */

/**
 * @typedef {Object} IServerSideGetRowsRequest
 * @property {number | undefined} startRow - First row requested or undefined for all rows.
 * @property {number | undefined} endRow - Index after the last row required row or undefined for all rows.
 * @property {ColumnVO[]} rowGroupCols - Columns that are currently row grouped.
 * @property {ColumnVO[]} valueCols - Columns that have aggregations on them.
 * @property {ColumnVO[]} pivotCols - Columns that have pivot on them.
 * @property {boolean} pivotMode - Defines if pivot mode is on or off.
 * @property {string[]} groupKeys - What groups the user is viewing.
 * more properties in original
 *
 * Original is the TypeScript file at {@link https://github.com/ag-grid/ag-grid/blob/latest/grid-community-modules/core/src/ts/interfaces/iServerSideDatasource.ts}
 */

/**
 * @typedef {Object} IServerSideDatasource
 * @property {(IServerSideGetRowsParams) => void} getRows
 *
 * Original is the TypeScript file at {@link https://github.com/ag-grid/ag-grid/blob/latest/grid-community-modules/core/src/ts/interfaces/iServerSideDatasource.ts}
 */

/**
 * @typedef {Object} IServerSideGetRowsParams
 * @property {IServerSideGetRowsRequest} request
 * @property {(params: LoadSuccessParams) => void} success
 * @property {() => void} fail
 *
 * Original is the TypeScript file at {@link https://github.com/ag-grid/ag-grid/blob/latest/grid-community-modules/core/src/ts/interfaces/iServerSideDatasource.ts}
 */

/**
 * @typedef {Object} LoadSuccessParams
 * @param {any[]} rowData
 * @param {number} [rowCount]
 *
 * Original is the TypeScript file at {@link https://github.com/ag-grid/ag-grid/blob/latest/grid-community-modules/core/src/ts/rowNodeCache/rowNodeBlock.ts}
 */